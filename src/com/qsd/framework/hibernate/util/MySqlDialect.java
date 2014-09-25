package com.qsd.framework.hibernate.util;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQL5Dialect;


/**
 * 针对MYSQL不认识Lob数据类型
 * 
 * @author suntongwei
 */
public class MySqlDialect extends MySQL5Dialect {

	public MySqlDialect() {
		super();
		registerHibernateType(-1, Hibernate.STRING.getName());  
	}
}
