package com.qsd.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Date Util
 * 
 * @author suntongwei
 * @version 1.0
 * @create at 2010-10-11
 */
public final class DateUtil {

	public static final long ONE_SECOND_TIME = 1l * 1000;
	public static final long ONE_MINUTE_TIME = ONE_SECOND_TIME * 60;
	public static final long ONE_HOUR_TIME = ONE_MINUTE_TIME * 60;
	public static final long ONE_DAY_TIME = ONE_HOUR_TIME * 24;
	public static final long YESTERDAY_TIME = ONE_DAY_TIME * 2;
	public static final long BEFORE_YESTERDAY_TIME = ONE_DAY_TIME * 3;
	public static final long ONE_WEEK_TIME = ONE_DAY_TIME * 7;
	
	/**
	 * 转换日期形式
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static final String DATE = "yyyy-MM-dd";
	public static final String TIME = "HH:mm:ss";
	public static final String HOUR = "HH:mm";
	public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 将时间字符串转为Date对象，时间字符串格式要求：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param str
	 * @return
	 */
	public static Date parseDate(String str) {
		return parseDate(str,DATE_TIME);
	}
	public static Date parseDate(String str, String format) {
		if (null == str)
			return null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = new Date(sdf.parse(str).getTime());
			return date;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Long parseDateLong(String str) {
		return parseDateLong(str, DATE_TIME);
	}
	public static Long parseDateLong(String str, String format) {
		if (null == str)
			return null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(str).getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static final int RANGE_DAY = 0x0;
	public static final int RANGE_MONTH = 0x1;
	public static final int RANGE_YEAR = 0x2;
	
	/**
	 * 取某一日期前后范围内的日期 直接调用格式为 DateUtil.getRangeDate("2010-01-01",-1,DateUtil.RANGE_DAY);
	 * 第3个参数可以不输入
	 * 
	 * @param date
	 *            日期格式(0000-00-00)
	 * @param range
	 *            可以接受2个参数，一个为必须输入，第二个为可选参数 第一个参数表示时间前后，大于0表示后，小于0表示前
	 *            第二个参数表示时间前后的年月日，0表示日，1表示月，2表示年，默认为0
	 * @author suntongwei
	 */
	public static Long getRangeDate(Long date, int... range) {

		try {
			if (null == date || "".equals(date))
				return null;

			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(date);

			int rangeflag = 0;
			if (range.length > 1)
				rangeflag = range[1];

			if (rangeflag == RANGE_DAY)
				cal.add(Calendar.DATE, range[0]);
			else if (rangeflag == RANGE_MONTH)
				cal.add(Calendar.MONTH, range[0]);
			else if (rangeflag == RANGE_YEAR)
				cal.add(Calendar.YEAR, range[0]);
			else
				return null;

			return cal.getTimeInMillis();

		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getRangeDate(String date, int... range) {
		return formatDateTime(getRangeDate(parseDate(date).getTime(),range));
	}
	
	/**
	 * 剩余时间
	 * 
	 * @return
	 */
	public static String getRemainderTime(long time) {
		String ret = "";
		if(time > ONE_DAY_TIME) {
			int dayNum = (int) (time / ONE_DAY_TIME);
			ret += dayNum + "天";
			time -= dayNum * ONE_DAY_TIME;
		}
		if(time > ONE_HOUR_TIME) {
			int hourNum = (int) (time / ONE_HOUR_TIME);
			ret += hourNum + "小时";
			time -= hourNum * ONE_HOUR_TIME;
		}
		if(time > ONE_MINUTE_TIME) {
			int minuteNum = (int) (time / ONE_MINUTE_TIME);
			ret += minuteNum + "分钟";
			time -= minuteNum * ONE_MINUTE_TIME;
		}
		if(time > ONE_SECOND_TIME) {
			int secondNum = (int) (time / ONE_SECOND_TIME);
			ret += secondNum + "秒";
			time -= secondNum * ONE_SECOND_TIME;
		}
		return ret;
	}
	
	
	/**
	 * 时间进行人性化转换
	 * 
	 * @return
	 */
	public static String formatBbsTime(long time) {
		long t = System.currentTimeMillis() - time;
		String ret = "";
		if(t < ONE_MINUTE_TIME) {
			int ts = (int) (t / ONE_SECOND_TIME);
			ret = ts + "秒前";
		} else if(t < ONE_HOUR_TIME) {
			int ts = (int) (t / ONE_MINUTE_TIME);
			ret = ts + "分钟前";
		} else if(t < ONE_DAY_TIME) {
			int ts = (int) (t / ONE_HOUR_TIME);
			ret = ts + "小时前";
		} else if(t < YESTERDAY_TIME) {
			ret = "昨天 " + formatHour(time);
		} else if(t < BEFORE_YESTERDAY_TIME) {
			ret = "前天 " + formatHour(time);
		} else if(t < ONE_WEEK_TIME) {
			int ts = (int) (t / ONE_DAY_TIME);
			ret = ts + "天前";
		} else {
			ret = formatDate(time);
		}
		return ret;
	}


	/**
	 * 比较日期是否相等,参数个数无限制，可以比较多个时间是否是同一天 参数个数视计算机能力为限,日期用-间隔 调用方法
	 * StringTool.isDate("2000-01-01","2000-1-1");
	 */
	public static boolean isDate(String... date) {

		if (date.length < 1)
			return false;

		String val = date[0];

		for (int i = 1; i < date.length; i++) 
			if (!val.equals(date[i])) return false;

		return true;
	}
	
	/**
	 * 是否是今天
	 * 
	 * @param time
	 * @return
	 */
	public static boolean isTotal(long time) {
		return formatDate(System.currentTimeMillis()).equals(formatDate(time));
	}
	
	/**
	 * 转换时间为LONG类型
	 * 
	 * @param date
	 * @return
	 */
//	public static Long getLongDate(String date){
//		
//		String[] datetime = date.split(" ");
//		if("".equals(datetime[0]))
//			return null;
//		
//		String[] dates = datetime[0].split("-");
//		
//		String[] time = null;
//		if(datetime.length == 2){
//			time = datetime[1].split(":");
//		}
//		
//		Calendar cal = Calendar.getInstance();
//		if(dates.length == 3){
//			if(time != null && time.length == 3){
//				cal.set(Integer.valueOf(dates[0]), Integer.valueOf(dates[1])-1, 
//						Integer.valueOf(dates[2]),Integer.valueOf(time[0]),
//						Integer.valueOf(time[1]),Integer.valueOf(time[2]));
//			} else {
//				cal.set(Integer.valueOf(dates[0]), Integer.valueOf(dates[1])-1, Integer.valueOf(dates[2]));
//			}
//		}
//		
//		return cal.getTimeInMillis();
//	}

	public static String formatDate(Date date) {
		return DateUtil.formatDate(date,DATE);
	}
	public static String formatDate(Long date){
		Date d = new Date(date);
		return DateUtil.formatDate(d);
	}
	
	public static String formatTime(Date date) {
		return DateUtil.formatDate(date,TIME);
	}
	public static String formatTime(Long date){
		Date d = new Date(date);
		return DateUtil.formatTime(d);
	}
	
	public static String formatDateTime(Date date) {
		return DateUtil.formatDate(date,DATE_TIME);
	}
	public static String formatDateTime(Long date){
		Date d = new Date(date);
		return DateUtil.formatDateTime(d);
	}
	
	public static String formatHour(Date date) {
		return DateUtil.formatDate(date,HOUR);
	}
	public static String formatHour(Long date){
		Date d = new Date(date);
		return DateUtil.formatHour(d);
	}
	
	public static String formatDate(Date date,String type) {
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		return sdf.format(date);
	}
	
	/**
	 * 获取当前日期 yyyy-MM-dd
	 */
	public static String getNowDate() {
		Calendar cal = Calendar.getInstance();
		String ret = cal.get(Calendar.YEAR) + "-"
				+ (cal.get(Calendar.MONTH) + 1) + "-"
				+ cal.get(Calendar.DAY_OF_MONTH);
		return ret;
	}

	/**
	 * 获取当前时间 HH:mm:ss
	 */
	public static String getNowTime() {
		Calendar cal = Calendar.getInstance();
		String ret = cal.get(Calendar.HOUR_OF_DAY) + ":"
				+ cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
		return ret;
	}

	/**
	 * 获取日期+时间 yyyy-MM-dd HH:mm:ss
	 */
	public static String getFullNowTime() {

		Calendar cal = Calendar.getInstance();

		String ret = cal.get(Calendar.YEAR) + "-"
				+ (cal.get(Calendar.MONTH) + 1) + "-"
				+ cal.get(Calendar.DAY_OF_MONTH) + " "
				+ cal.get(Calendar.HOUR_OF_DAY) + ":"
				+ cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
		
		return ret;
	}
	
	public static void main(String... args){
		System.out.println(System.currentTimeMillis());
	}
}
