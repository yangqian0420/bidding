package com.system.util;

public class AppConstants {

	 //E 中性盘 
    public static String TRAN_TYPE_NEUTRAL = "E";
    // U 买盘  
    public static String TRAN_TYPE_BUY = "U";
    // D 卖盘 
    public static String TRAN_TYPE_SELL = "D";
    
    //实时股票数据请求地址
    public static String realTimeDataUrl = "http://hq.sinajs.cn/list=%s";
    //第三个获取昨日成交信息
    public static String yesterDayDataUrl = "http://api.waditu.com";
    
    //50万大额单
    public static String BIG_AMOUNT_URL = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_Bill.GetBillList?symbol=%s&sort=ticktime&asc=1&volume=0&amount=500000&type=0&day=%s";
    //400手大量单
    public static String BIG_VOLUME_URL = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_Bill.GetBillList?symbol=%s&sort=ticktime&asc=1&volume=40000&amount=0&type=0&day=%s";
    //大单竞价
    public static String BIG_BIDDING_URL = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_Bill.GetBillList?symbol=%s&num=1&sort=ticktime&asc=1&volume=40000&amount=0&type=0&day=%s";
    //竞价
    public static String GRININFO_URL = "http://vip.stock.finance.sina.com.cn/quotes_service/view/CN_TransListV2.php?symbol=%s&rn=%s";
    
    //分时数据
    public static String FENHOUREDATA_URL = "http://quotes.sina.cn/cn/api/json_v2.php/CN_MarketDataService.getKLineData?symbol=%s&scale=%s&ma=no&datalen=%s";
    
    //开盘数据
    public static String OPEN_KEY = "OPEN:%s" ;
    //收盘数据
    public static String CLOSE_KEY = "CLOSE:%s" ;
    
    //竞价数据
    public static String  BIDDING_KEY=  "BIDDING:%s";
    
    //大单key
    public static String  BIGTRANCATION_KEY = "BIG:BIG_%s:%s"; 
    
    //
    public static String HOTTYPE_KEY= "HOTTYPE:%s" ;
    
    //当日卡位预期数据
    //public static String CARD_POSTION = "CARD_POSTION:%s";
    
    //日线
    public static String LINE_TYPE_DAY = "1";
    
    //60分线
    public static String LINE_TYPE_60MINIT = "2";
    
    //底分型
    public static String PARTING_TYPE_LOW = "1";

    //顶分型
    public static String PARTING_TYPE_TOP = "2";
    
    //交易类型卡位预期
    public static String  TRADE_TYPE_1 = "1" ;
    //交易类型 回马枪
    public static String  TRADE_TYPE_2 = "2" ;
    //交易类型 回马枪一字板涨停
    public static String  TRADE_TYPE_3 = "3" ;
    
    //交易类型买入
    public static String  TRADE_ACTION_BUY = "0" ;
    //交易类型卖出
    public static String  TRADE_ACTION_SELL = "1" ;
    
    //指数
    public static String  LINE_TYPE_INDEX = "0" ;
    //交易类型卖出
    public static String  LINE_TYPE_STOCK = "1" ;
    
    //交易状态 进行中
    public static Integer  TRADE_STATUE_RUNNING = 0 ;
    
    //交易状态 完成
    public static Integer  TRADE_STATUE_OVER = 1 ;
}
