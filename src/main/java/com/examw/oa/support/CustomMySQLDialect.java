package com.examw.oa.support;

import org.hibernate.dialect.MySQLDialect;

/**
 * 自定义MySql方言。
 * @author yangyong.
 * @since 2014-07-01.
 */
public class CustomMySQLDialect extends MySQLDialect {
	/**
	 * 构造函数。
	 */
	public CustomMySQLDialect(){
		super();
		this.registerFunction("bitand", new BitAndFunction());
	}
}