package com.qsd.framework.json;

import org.codehaus.jackson.map.ObjectMapper;


/**
 * Json对象转换扩展
 * 
 * @author suntongwei
 */
public class JsonObjectMapper extends ObjectMapper {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4271257253779197211L;

	public JsonObjectMapper() {
		registerModule(new HibernateModule());
	}

}
