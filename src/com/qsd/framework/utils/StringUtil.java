package com.qsd.framework.utils;

/**
 * String Util
 * 
 * @author suntongwei
 * @version 1.0
 * @create at 2010-10-11
 */
public final class StringUtil {

	/**
	 * GBK编码
	 */
	public static final String GBK = "GBK";
	/**
	 * GB2312编码
	 */
	public static final String GB2312 = "gb2312";
	/**
	 * UTF-8编码
	 */
	public static final String UTF_8 = "utf-8";
	/**
	 * Unicode编码
	 */
	public static final String UNICODE = "Unicode";
	
	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str){
		return null == str || "".equals(str.trim());
	}
	
	public static boolean isNotNull(String str){
		return !StringUtil.isNull(str);
	}
	
	/**
	 * 字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String... strs){
		if(strs != null && strs.length > 0){
			for(String str : strs) {
				if(isNull(str)) return true;
			}
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 
	 * 
	 * @param val
	 * @param defaultVal
	 * @return
	 */
	public static String parseString(Object val, String defaultVal) {
		if(null == val || "".equals(val) || "NULL".equalsIgnoreCase(val.toString())) {
			return defaultVal;
		}
		return String.valueOf(val);
	}
	
	/**
	 * 字符转换
	 * 
	 * @param val
	 * @return
	 */
	public static String valueOf(Object val) {
		return val != null ? String.valueOf(val) : null;
	}
	
	/**
	 * 转换ID
	 * 
	 * @param ids
	 * @return
	 */
	public static String toIdsParams(long[] ids) {
		StringBuffer sb = new StringBuffer("");
		for(long id : ids) {
			sb.append("," + id);
		}
		if(sb.length() > 0) {
			return sb.substring(1, sb.length());
		}
		return "";
	}
}
