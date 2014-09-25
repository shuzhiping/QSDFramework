package com.qsd.framework.hibernate;

import java.io.Serializable;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * JPA数据库操作持久化
 * 
 * @author suntongwei
 * @version 1.0
 * @create at Jul 28, 2011
 */
public abstract class JpaDaoSupport<T,ID extends Serializable> extends JpaDaoBase<T,ID> {
	
	/**
	 * 注入EntityManagerFactory
	 * 
	 * @param entityManagerFactory
	 */
	@Autowired
	@Qualifier("entityManagerFactory")
	protected void setJpaEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		super.setEntityManagerFactory(entityManagerFactory);
	}
}
