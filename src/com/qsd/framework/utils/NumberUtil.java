package com.qsd.framework.utils;

import java.math.BigDecimal;

/**
 * Number Util
 * 
 * @author suntongwei
 * @version 1.0
 * @create at 2010-10-11
 */
public final class NumberUtil {

	/**
	 * 判断是否是ID
	 * 
	 * @param id
	 * @return
	 */
	public static boolean isNullId(Long id) {
		return null == id || id < 1;
	}
	
	/**
	 * 判断是否是正数
	 * 
	 * @return
	 */
	public static boolean isPositive(Long num) {
		if(num != null && num.longValue() > 0) {
			return true;
		}
		return false;
	}
	public static boolean isPositive(Integer num) {
		if(num != null && num.longValue() > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 四舍五入方法
	 * 默认为保存2位小数
	 * 
	 * @param d
	 * @param s
	 * @return
	 */
	public static Double roundDouble(Double d) {
		if (d == null)
			return d;
		else
			return roundDouble(d, 2);
	}

	/**
	 * 四舍五入方法
	 * 
	 * @param d
	 * @param s
	 * @return
	 */
	public static Double roundDouble(Double d, int s) {
		
		if (d == null)
			return null;
		
		BigDecimal b = new BigDecimal(d);
		return b.setScale(s, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 对象和数字类型的转换
	 * 
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	public static Long parseLong(Object obj, long defaultValue) {
		try {
			return Long.valueOf(obj.toString());
		} catch(NullPointerException e) {
			return defaultValue;
		} catch(NumberFormatException e) {
			return defaultValue;
		}
	}
	public static Double parseDouble(Object obj, double defaultValue) {
		try {
			return Double.valueOf(obj.toString());
		} catch(NullPointerException e) {
			return defaultValue;
		} catch(NumberFormatException e) {
			return defaultValue;
		}
	}
	public static Integer parseInteger(Object obj, int defaultValue) {
		try {
			return Integer.valueOf(obj.toString());
		} catch(NullPointerException e) {
			return defaultValue;
		} catch(NumberFormatException e) {
			return defaultValue;
		}
	}
}
