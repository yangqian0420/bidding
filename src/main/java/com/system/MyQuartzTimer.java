package com.system;

import com.system.dto.BiddingInfo;
import com.system.dto.DragonInfo;
import com.system.dto.PageInfo;
import com.system.util.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class MyQuartzTimer {

	private static Logger logger = LoggerFactory.getLogger(MyQuartzTimer.class);

    public void doTask() throws InterruptedException {
        if(HolidayUtils.getDateMap(new Date()).get(DateUtil.format(new Date(), "yyyyMMdd")) == 0) {
            logger.info("今天为周末或假期，不执行。");
            return;
        }
        SqlSession session = getSqlSession();
        long countDragon =session.selectOne("countDragon");
        logger.info("dragon数据条数为====>{}", countDragon);
        Map<String, String> codeMap = new HashMap<>();
        Set<String> failedCode = new HashSet();
        List<DragonInfo> dragons = session.selectList("selectAllDragonCodes");
        for(DragonInfo dragon : dragons) {
            codeMap.put(dragon.getDragon1_code(), dragon.getDragon1_name());
            codeMap.put(dragon.getDragon2_code(), dragon.getDragon2_name());
            codeMap.put(dragon.getDragon3_code(), dragon.getDragon3_name());
        }
        //查询龙虎数据，如果报错，则加入失败的code集合
        for(String code : codeMap.keySet()) {
            try{
                if(null == code || code.trim().equals("")) continue;
                code = code.trim();
                BiddingInfo info = getRomotReponseData(code, codeMap);
                saveBiddingInfo(info, session);
                //睡眠任意时间再调用
                Random random = new Random();
                Thread.sleep(random.nextInt(1000));
            } catch (Exception e) {
                logger.info("获取竞价信息失败====>", e);
                failedCode.add(code);
                session.commit();
                Thread.sleep(5*60*1000);
            }
        }
        //失败集合不为空，循环调用至集合为空
        Iterator<String> iterator = failedCode.iterator();
        while(iterator.hasNext()){
            String code = iterator.next();
            try{
                if(null == code || code.trim().equals("")) continue;
                code = code.trim();
                BiddingInfo info = getRomotReponseData(code, codeMap);
                saveBiddingInfo(info, session);
                iterator.remove();
                //睡眠一段时间后调用
                Random random = new Random();
                Thread.sleep(random.nextInt(1000));
            } catch (Exception e) {
                logger.info("获取竞价信息失败====>", e);
                session.commit();
                Thread.sleep(5*60*1000);
            }
        }
        session.commit();
        session.close();
        logger.info("获取竞价信息正常结束====>");
    }

    private SqlSession getSqlSession(){
        String resources = "mybatis-config.xml";
        Reader reader=null;
        try {
            reader= Resources.getResourceAsReader(resources);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlMapper=new SqlSessionFactoryBuilder().build(reader);
        SqlSession session=sqlMapper.openSession();
        return session;
    }

    private BiddingInfo getRomotReponseData(String code, Map<String, String> codeMap) {

        String codeType = GetCodeTypeUtils.getCodeType(code);

        String tsCode = "";

        if (code.toLowerCase().contains(codeType)) {
            tsCode = codeType + code.toLowerCase().replace(codeType, "");
        } else {
            tsCode = codeType + code;
        }
        String url = String.format(AppConstants.GRININFO_URL, tsCode, System.currentTimeMillis());

        String response = HttpClientService.sendGetRequest(url, "gb2312");

        if (StringUtils.isEmpty(response)) {
            return null;
        } else {
            BiddingInfo info = parseResponse(code, response, codeMap);

            return info;
        }
    }

    private static BiddingInfo parseResponse(String code, String response, Map<String, String> codeMap) {

        response = response.replaceAll("\\s+", "");

        String[] array = response.split(";");

        List<String> list = Arrays.asList(array);
        if (list.isEmpty()) {

            return null;
        }
        String str = list.get(list.size() - 2);
        String content = str.substring(str.indexOf("(") + 1, str.indexOf(")")).replaceAll("'", "");
        if (StringUtils.isEmpty(content)) {

            return null;
        }
        String[] cs = content.split(",");
        BiddingInfo info = new BiddingInfo();
        info.setSymbol(code);
        info.setName(codeMap.containsKey(code) ? codeMap.get(code) : "");
        info.setPrice(Double.parseDouble(cs[2]));
        info.setVolume(Double.parseDouble(cs[1]) / 100);
        BigDecimal decimal = new BigDecimal(info.getPrice()).multiply(new BigDecimal(info.getVolume()).divide(new BigDecimal(100)));
        BigDecimal amount = decimal.setScale(2, RoundingMode.CEILING);
        info.setAmount(amount.doubleValue());
        info.setKind(cs[3].substring(0, 1));
        info.setBidding_time(DateUtil.parse(DateUtil.formatDate(new Date()).substring(0, 10).concat(" ").concat(cs[0]),DateUtil.NORM_DATETIME_PATTERN));
        return info;
    }

    private void saveBiddingInfo(BiddingInfo info, SqlSession session){
        if(null == info) return;
        session.insert("insertBiddingInfo", info);
    }
}
