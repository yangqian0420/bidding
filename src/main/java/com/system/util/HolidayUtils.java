package com.system.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.system.dto.YesterDayParamsVo;

import java.util.*;

/**
 * @version 1.0
 * @ClassName HolidayUtils
 * @Description
 * @Author 74981
 * @Date 2018/9/27 14:30
 */
public class HolidayUtils {
    
	public static Map<String,Integer> getDateMap (Date date){
		 YesterDayParamsVo vo = new YesterDayParamsVo() ;
		 vo.setApi_name("trade_cal");
		 JSONObject params = new JSONObject();
		 params.put("end_date", DateUtil.format(date, "yyyyMMdd"));
		 vo.setParams(params);
		 JSONObject responseJson = HttpClientService.sendPostJson(JSONObject.parseObject(JSON.toJSONString(vo)), AppConstants.yesterDayDataUrl,"UTF-8");
		 JSONObject data = responseJson.getJSONObject("data");
		 JSONArray itemsArray = data.getJSONArray("items");
		 Map<String,Integer> map =new HashMap<String,Integer>();
		 for(int i= 0;i< itemsArray.size() ; i++) {
			 
			 List<Object> list = itemsArray.getObject(i, List.class) ;
			 if(!list.isEmpty()) {
				 
				 String time = list.get(1).toString();
				 Integer value = Integer.parseInt(list.get(2).toString());
				 map.put(time, value);
			 }
		 }
		Map<String,Integer> sortedMapByKey = new TreeMap<String,Integer>();
	    
	    sortedMapByKey = new TreeMap<String, Integer>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				
				return o2.compareTo(o1);
			}
			
		});
		
	    sortedMapByKey.putAll(map);
	    
	    return sortedMapByKey;
	}
    
}