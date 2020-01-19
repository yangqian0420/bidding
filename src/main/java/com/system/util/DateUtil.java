package com.system.util;

import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类
* @ClassName: DateUtil 
* @Description: 日期处理类
* @author CHENZENGZHE
* @date Aug 24, 2017 9:11:47 AM 
* @copyright 西安帕克因网络科技有限公司
 */
public final class DateUtil {
	/**
	 * 秒
	 */
	public final static long MS = 1;
	/**
	 * 毫秒
	 */
	public final static long SECOND_MS = MS * 1000;
	/**
	 * 分
	 */
	public final static long MINUTE_MS = SECOND_MS * 60;
	/**
	 * 小时
	 */
	public final static long HOUR_MS = MINUTE_MS * 60;
	/**
	 * 天
	 */
	public final static long DAY_MS = HOUR_MS * 24;
	/**
	 * 格式化方式
	 */
	public final static String NORM_DATE_PATTERN = "yyyy-MM-dd";
	/**
	 * 格式化方式
	 */
	public final static String NORM_TIME_PATTERN = "HH:mm:ss";
	/**
	 * 格式化方式
	 */
	public final static String NORM_DATETIME_NO_SS_PATTERN = "yyyy-MM-dd HH:mm";
	/**
	 * 格式化方式
	 */
	public final static String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/**格式化方式
	 * 
	 */
	public final static String HTTP_DATETIME_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";
	
	private static ThreadLocal<SimpleDateFormat> NORM_DATE_FORMAT = new ThreadLocal<SimpleDateFormat>(){
		synchronized protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(NORM_DATE_PATTERN);
		};
	};
	private static ThreadLocal<SimpleDateFormat> NORM_TIME_FORMAT = new ThreadLocal<SimpleDateFormat>(){
		synchronized protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(NORM_TIME_PATTERN);
		};
	};
	private static ThreadLocal<SimpleDateFormat> NORM_DATETIME_FORMAT = new ThreadLocal<SimpleDateFormat>(){
		synchronized protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(NORM_DATETIME_PATTERN);
		};
	};
	private static ThreadLocal<SimpleDateFormat> HTTP_DATETIME_FORMAT = new ThreadLocal<SimpleDateFormat>(){
		synchronized protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(HTTP_DATETIME_PATTERN, Locale.US);
		};
	};
	/**
	 * 当前时间格式化
	 * @since 1.0 
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:51:23
	 */
	public static String now() {
		return formatDateTime(new DateTime());
	}
	/**
	 * 当前时间格式化
	 * @since 1.0 
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:51:37
	 */
	public static Timestamp getNow(){
		return new Timestamp(System.currentTimeMillis()) ;
	}
	
	/**
	 * 当前时间格式化
	 * @since 1.0 
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:51:37
	 */
   public static long stringToLong(String strTime, String formatType)  
            throws ParseException {  
        Date date = stringToDate(strTime, formatType); // String类型转成date类型  
        if (date == null) {  
            return 0;  
        } else {  
            long currentTime = dateToLong(date); // date类型转成long类型  
            return currentTime;  
        }  
    }  
   
   public static Date stringToDate(String strTime, String formatType)  
           throws ParseException {  
       SimpleDateFormat formatter = new SimpleDateFormat(formatType);  
       Date date = null;  
       date = formatter.parse(strTime);  
       return date;  
   }  
   
   public static long dateToLong(Date date) {  
       return date.getTime();  
   }
	/**
	 * @since 1.0 
	 * @param mi long
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:51:45
	 */
	public static Timestamp getNow(long mi){
		return new Timestamp(System.currentTimeMillis()+mi*1000) ;
	}
	/**
	 * 今天
	 * @since 1.0 
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:51:53
	 */
	public static String today() {
		return formatDate(new DateTime());
	}
	/**
	 * 
	 * @since 1.0 
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:52:04
	 */
	public static DateTime date() {
		return new DateTime();
	}
	/**
	 * @since 1.0 
	 * @param date 传入日期类型
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:52:16
	 */
	public static DateTime date(long date) {
		return new DateTime(date);
	}
	/**
	 * 是否是日期格式
	 * @since 1.0 
	 * @param date 日期类型
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:52:28
	 */
	public static boolean isValidateDateFormat(Object date) {
		if(date instanceof Date){
			return true;
		}
		String _date = String.valueOf(date);
		if (_date == null || (_date.length() != 8 && _date.length() != 10))
			return false;
		String[] formats = getDateFormat();
		try {
			DateUtils.parseDate(_date, formats);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * @since 1.0 
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:52:45
	 */
	public static String[] getDateFormat() {
		return new String[] { "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd",
				"yyyy-MM-ddHH:mm:ss", "yyyy-MM-dd HH:mm:ss",
				"yyyyMMddHH:mm:ss", "yyyy/MM/ddHH:mm:ss" };
	}
	/**
	 * @since 1.0 
	 * @param date 参数
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:52:48
	 */
	public static String yearAndSeason(Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		return yearAndSeason(cal);
	}
	/**
	 * 
	* @Title: year 
	* @Description: 获取年份
	* @param @param date
	* @param @return    参数说明
	* @return String    返回类型 
	* @throws
	 */
	public static String year(Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		return year(cal);
	}
	/**
	 * @since 1.0 
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:52:56
	 */
	public static LinkedHashSet<String> yearAndSeasons(Date startDate, Date endDate) {
		final LinkedHashSet<String> seasons = new LinkedHashSet<String>();
		if(startDate == null || endDate == null) {
			return seasons;
		}
		
		
		final Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		while(true) {
			if(startDate.after(endDate)) {
				startDate = endDate;
			}
			
			seasons.add(yearAndSeason(cal));
			
			if(startDate.equals(endDate)) {
				break;
			}
			
			cal.add(Calendar.MONTH, 3);
			startDate = cal.getTime();
		}
		
		return seasons;
	}
	/**
	 * @since 1.0 
	 * @param date 时间
	 * @param format 格式方式
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:53:08
	 */
	public static String format(Date date, String format){
		return new SimpleDateFormat(format).format(date);
	}
	/**
	 * 时间
	 * @since 1.0 
	 * @param date 转化参数
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:53:20
	 */
	public static String formatDateTime(Date date) {
		return NORM_DATETIME_FORMAT.get().format(date);
	}
	
	/**
	 * @since 1.0 
	 * @param date 时间
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:53:24
	 */
	public static String formatDate(Date date) {
		return NORM_DATE_FORMAT.get().format(date);
	}
	/**
	 * @since 1.0 
	 * @param date 时间
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:53:33
	 */
	public static String formatHttpDate(Date date) {
		return HTTP_DATETIME_FORMAT.get().format(date);
	}
	/**
	 * @since 1.0 
	 * @param dateStr 字符串格式时间
	 * @param simpleDateFormat 格式类型
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:53:42
	 */
	public static DateTime parse(String dateStr, SimpleDateFormat simpleDateFormat) {
		try {
			return new DateTime(simpleDateFormat.parse(dateStr));
		} catch (Exception e) {
			throw new RuntimeException(String.format("Parse [{}] with format [{}] error!", dateStr, simpleDateFormat.toPattern()), e);
		}
	}
	/**
	 * @since 1.0 
	 * @param dateString 事件类型
	 * @param format 格式方式
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:53:58
	 */
	public static DateTime parse(String dateString, String format){
		return parse(dateString, new SimpleDateFormat(format));
	}
	/**
	 * @since 1.0 
	 * @param dateString 字符串时间
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:54:18
	 */
	public static DateTime parseDateTime(String dateString){
		return parse(dateString, NORM_DATETIME_FORMAT.get());
	}
	/**
	 * @since 1.0 
	 * @param dateString 字符串时间
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:54:49
	 */
	public static DateTime parseDate(String dateString){
		return parse(dateString, NORM_DATE_FORMAT.get());
	}
	
	/**
	 * @since 1.0 
	 * @param timeString 字符串时间
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:54:52
	 */
	public static DateTime parseTime(String timeString){
		return parse(timeString, NORM_TIME_FORMAT.get());
	}
	/**
	 * @since 1.0 
	 * @param dateStr 字符串时间
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:54:54
	 */
	public static DateTime parse(String dateStr) {
		if(null == dateStr) {
			return null;
		}
		dateStr = dateStr.trim();
		int length = dateStr.length();
		try {
			if(length == NORM_DATETIME_PATTERN.length()) {
				return parseDateTime(dateStr);
			}else if(length == NORM_DATE_PATTERN.length()) {
				return parseDate(dateStr);
			}else if(length == NORM_TIME_PATTERN.length()){
				return parseTime(dateStr);
			}else if(length == NORM_DATETIME_NO_SS_PATTERN.length()){
				return parse(dateStr, NORM_DATETIME_NO_SS_PATTERN);
			}
		}catch(Exception e) {
			throw new RuntimeException(String.format("Parse [{}] with format normal error!", dateStr));
		}
		
		throw new RuntimeException(String.format(" [{}] format is not fit for date pattern!", dateStr));
	}
	/**
	 * @since 1.0 
	 * @param date 转化时间
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:55:23
	 */
	public static DateTime getBeginTimeOfDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return new DateTime(calendar.getTime());
	}
	/**
	 * @since 1.0 
	 * @param date 转化时间
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:55:33
	 */
	public static DateTime getEndTimeOfDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return new DateTime(calendar.getTime());
	}
	/**
	 * 昨天
	 * @since 1.0 
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:55:42
	 */
	public static DateTime yesterday() {
		return offsiteDay(new DateTime(), -1);
	}
	/**
	 * 今天
	 * @since 1.0 
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:55:52
	 */
	public static DateTime lastWeek() {
		return offsiteWeek(new DateTime(), -1);
	}
	/**
	 * 上个月的几天
	 * @since 1.0 
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:55:57
	 */
	public static DateTime lastMouth() {
		return offsiteMonth(new DateTime(), -1);
	}
	
	/**
	 * @since 1.0 
	 * @param date 匹配时间
	 * @param offsite 偏移量
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:56:07
	 */
	public static DateTime offsiteDay(Date date, int offsite) {
		return offsiteDate(date, Calendar.DAY_OF_YEAR, offsite);
	}
	/**
	 * @since 1.0 
	 * @param date 转化时间
	 * @param offsite 偏移量
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:56:09
	 */
	public static DateTime offsiteWeek(Date date, int offsite) {
		return offsiteDate(date, Calendar.WEEK_OF_YEAR, offsite);
	}
	/**
	 * @since 1.0 
	 * @param date 转化时间
	 * @param offsite 偏移量
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:56:12
	 */
	public static DateTime offsiteMonth(Date date, int offsite) {
		return offsiteDate(date, Calendar.MONTH, offsite);
	}
	
	/**
	 * @since 1.0 
	 * @param date 转化时间
	 * @param calendarField 日历日期
	 * @param offsite 偏移量
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:57:00
	 */
	public static DateTime offsiteDate(Date date, int calendarField, int offsite){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(calendarField, offsite);
		return new DateTime(cal.getTime());
	}
	/**
	 * @since 1.0 
	 * @param subtrahend 开始时间
	 * @param minuend 结束时间
	 * @param diffField 格式化单位
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:57:02
	 */
	public static long diff(Date subtrahend, Date minuend, long diffField){ 
	  long diff = minuend.getTime() - subtrahend.getTime();
	  return diff/diffField; 
	}
	private static String yearAndSeason(Calendar cal) {
		return new StringBuilder().append(cal.get(Calendar.YEAR)).append(cal.get(Calendar.MONTH) / 3 + 1).toString();
	}
	private static String year(Calendar cal) {
		return new StringBuilder().append(cal.get(Calendar.YEAR)).toString();
	}

	/**
	 * 通过时间秒毫秒数判断两个时间的间隔-天数
	 * @param date1
	 * @param date2
	 * @return a
	 */
	public static int differentDaysByMillisecond(Long date1, Long date2){
		int days = (int)((date2 - date1) / DAY_MS);
		return days;
	}

	/**
	 * 通过时间秒毫秒数判断两个时间的间隔-小时
	 * @param date1
	 * @param date2
	 * @return a
	 */
	public static int differentDaysByMillisecondHour(Long date1, Long date2){
		int hour = (int)((date2 - date1) / HOUR_MS);
		return hour;
	}

	/**
	 * 通过时间秒毫秒数判断两个时间的间隔-分钟
	 * @param date1
	 * @param date2
	 * @return a
	 */
	public static int differentDaysByMillisecondMinute(Long date1, Long date2){
		int minute = (int)((date2 - date1) / MINUTE_MS);
		return minute;
	}
	
    //获取某月的结束时间
    public static DateTime getEndTimeOfMonth(Date d) {
    	  Calendar calendar = Calendar.getInstance();
          if(null != d) calendar.setTime(d);
          calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
          int day = calendar.getActualMaximum(5);
          calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), day, 23, 59, 59);
        return new DateTime(calendar.getTimeInMillis());
    }
    //获取某月的开始时间
    public static DateTime getStartTimeOfMonth(Date d) {
           Calendar calendar = Calendar.getInstance();
           if(null != d) calendar.setTime(d);
           calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1, 0, 0, 0);
           return new DateTime(calendar.getTimeInMillis());
     }
	/** 
	* @Title: getLastDay 
	* @Description: 获取当月第一天
	* @param calendar
	* @param month
	* @return Date
	*/
	public static Date getFirstDay(Integer month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month - 1);
        int minday =calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, minday);
        return calendar.getTime();
	}

	/** 
	* @Title: getFirstDay 
	* @Description: 获取当月最后一天
	* @param calendar
	* @param month
	* @return Date
	*/
	public static Date getLastDay(Integer month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month - 1);
		int maxday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, maxday);
		return calendar.getTime();
	}
	/***
	* @Title: getNear30Day 
	* @Description: 获取当前近30天
	* @return Date 三十天前时间
	 */
	public static Date getNear30Day(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -30);
		return calendar.getTime();
	}
	
	/**
	 * 两个时间相差距离多少天多少小时多少分多少秒
	 * 
	 * @param str1 时间参数 1 
	 * @param str2 时间参数 2 
	 * @return String 返回值为：xx天xx小时xx分xx秒
	 */
	public static String getDistanceTime(long oldTime, long newTime) {

		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;	
		long diff;
		if (oldTime < newTime) {
			diff = newTime - oldTime;
		} else {
			diff = oldTime - newTime;
		}
		day = diff / DAY_MS;
		hour = diff / HOUR_MS - day * 24;
		min = diff / MINUTE_MS - day * 24 * 60 - hour * 60;
		sec = (diff - day * DAY_MS - hour * HOUR_MS - min * MINUTE_MS)/SECOND_MS;
		int nowYear = Integer.parseInt(DateUtil.year(new Date()));
		int year = Integer.parseInt(DateUtil.year(new Date(oldTime)));
		long current=System.currentTimeMillis();//当前时间毫秒数
        long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒
		if(year < nowYear) {
			return DateUtil.formatDate(new Date(oldTime));
		}
		if(day >= 2){
			return DateUtil.format(new Date(oldTime), "MM-dd");
		}
		if(day <= 1 && oldTime <= zero){
			return "昨天";
		}
		if(hour>0){
			return hour + "小时前";
		}
		if(min>0){
			return min + "分钟前";
		}
		if(sec>0){
			return sec + "秒前";
		}
		return "";
	}
	
	/**
	 * 两个时间相差剩余多少天多少小时多少分多少秒
	 * 
	 * @param str1 时间参数 1 
	 * @param str2 时间参数 2 
	 * @return String 返回值为：剩余xx天xx小时xx分xx秒
	 */
	public static String getRemainTime(long time1, long time2) {

		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;	
		long diff;
		if (time1 < time2) {
			diff = time2 - time1;
		} else {
			diff = time1 - time2;
		}
		day = diff / DAY_MS;
		hour = diff / HOUR_MS - day * 24;
		min = diff / MINUTE_MS - day * 24 * 60 - hour * 60;
		sec = (diff - day * DAY_MS - hour * HOUR_MS - min * MINUTE_MS)/SECOND_MS;
		String str = "" ;
		if(day>0){
			str += day + "天";
		}
		if(hour>0){
			str +=  hour + "小时";
		}
		if(min>0){
			str +=  min + "分钟";
		}
//		if(sec>0){
//			str +=  sec + "秒";
//		}
		return str ;
	}
	
	public static void main(String[] agrs){
		String s1 = "2018-1-8 11:42:10";
		Date d1 = parseDateTime(s1);
		System.out.println(getRemainTime(d1.getTime(),new Date().getTime()));
	}
	
}

class DateTime  extends Date{
	/** 
	* @Fields serialVersionUID : TODO(描述这个字段表达了什么) 
	*/ 
	private static final long serialVersionUID = 1L;

	/**
	 * @since 1.0 
	 * @param date 欲转化的对象
	 * @return
	 * <br><b>作者： @author CHENZENGZHE</b>
	 * <br>创建时间：2017年6月5日 下午4:48:53
	 * 类型转化
	 */
	public static DateTime parse(Date date) {
		return new DateTime(date);
	}
	
	/**
	 * 
	 */
	public DateTime() {
		super();
	}
	
	/**
	 * @param date 参数
	 */
	public DateTime(Date date) {
		this(date.getTime());
	}
	/**
	 * long转为datetime
	 * @param timeMillis long类型
	 */
	public DateTime(long timeMillis) {
		super(timeMillis);
	}
	
	@Override
	public String toString() {
		return DateUtil.formatDateTime(this);
	}
}
