package com.qsd.framework.utils;

import java.io.IOException;
import java.io.InputStream;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;

import com.qsd.framework.json.JsonObjectMapper;


/**
 * Json 转换类
 * 
 * @author suntongwei
 * @version 1.0
 * @create at 2011-6-14
 */
public class JsonUtil {

	/**
	 * 把指定对象转换成JSON对象
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		try {
			JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
			return jsonObjectMapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String toJson(String obj) {
		try {
			JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
			return jsonObjectMapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 把JSON对象转换成指定对象
	 * 
	 * @param <T>
	 * @param obj
	 * @param cls
	 * @return
	 */
	public static <T> T fromJson(InputStream is, Class<T> cls) {
		try {
			if(is != null) {
				JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
				return jsonObjectMapper.readValue(is, cls);
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JsonNode fromJson(InputStream is) {
		try {
			if(is != null) {
				JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
				return jsonObjectMapper.readTree(is);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 把JSON对象转换成指定对象
	 * 
	 * @param <T>
	 * @param obj
	 * @param cls
	 * @return
	 */
	public static <T> T fromJson(String obj, Class<T> cls) {
		try {
			if(!StringUtil.isNull(obj)) {
				JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
				return jsonObjectMapper.readValue(obj, cls);
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}	
}
