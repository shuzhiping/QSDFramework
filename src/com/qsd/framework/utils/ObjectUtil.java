package com.qsd.framework.utils;

/**
 * 
 * 
 * @author suntongwei
 */
public final class ObjectUtil {
	
	
	public static Long[] changeIds(String idsStr) {
		return ObjectUtil.changeIds(idsStr,",");
	}
	
	public static Long[] changeIds(String idsStr, String opt) {
		String[] ids = idsStr.split(opt);
		Long[] idsLong = new Long[ids.length];
		for(int i = 0; i < ids.length; i++) {
			idsLong[i] = Long.valueOf(ids[i]);
		}
		return idsLong;
	}
}
