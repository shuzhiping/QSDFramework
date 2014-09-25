package com.qsd.framework.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 集合工具类
 * 
 * @author suntongwei
 */
public final class ArrayUtil {

	/**
	 * 判断集合是否为空
	 * 
	 * @param <T>
	 * @param collection
	 * @return
	 */
	public static <T> boolean isEmpty(Collection<T> collection){
		return null == collection || collection.isEmpty();
	}
	
	/**
	 * 判断数组是否为空
	 * 
	 * @param <T>
	 * @param objs
	 * @return
	 */
	public static <T> boolean isEmpty(T[] objs) {
		return !(objs != null && objs.length > 0);
	}
	
	/**
	 * 判断素组是否为空
	 * 
	 * @param <T>
	 * @param ids
	 * @return
	 */
	public static boolean isEmpty(long[] ids) {
		return !(ids != null && ids.length > 0);
	}
	
	/**
	 * 判断Map是否为空
	 * 
	 * @param <T>
	 * @param <K>
	 * @param map
	 * @return
	 */
	public static <T,K> boolean isEmpty(Map<T,K> map) {
		return !(map != null && map.size() > 0);
	}
}
