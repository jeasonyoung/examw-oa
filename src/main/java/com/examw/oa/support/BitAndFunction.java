package com.examw.oa.support;

import java.util.List;

import org.hibernate.QueryException;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.engine.spi.Mapping;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

/**
 * 支持位与运算&。
 * @author yangyong.
 * @since 2014-07-01.
 */
public class BitAndFunction implements SQLFunction {
	/*
	 * 是否有参数。
	 * @see org.hibernate.dialect.function.SQLFunction#hasArguments()
	 */
	@Override
	public boolean hasArguments() {
		return true;
	}
	/*
	 * 是否支持有括号没有参数。
	 * @see org.hibernate.dialect.function.SQLFunction#hasParenthesesIfNoArguments()
	 */
	@Override
	public boolean hasParenthesesIfNoArguments() {
		return true;
	}
	/*
	 * 返回类型。
	 * @see org.hibernate.dialect.function.SQLFunction#getReturnType(org.hibernate.type.Type, org.hibernate.engine.spi.Mapping)
	 */
	@Override
	public Type getReturnType(Type firstArgumentType, Mapping mapping) throws QueryException {
		return  StandardBasicTypes.INTEGER;
	}
	/*
	 * 函数处理。
	 * @see org.hibernate.dialect.function.SQLFunction#render(org.hibernate.type.Type, java.util.List, org.hibernate.engine.spi.SessionFactoryImplementor)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String render(Type firstArgumentType, List arguments, SessionFactoryImplementor factory) throws QueryException {
		if(arguments.size() != 2) throw new IllegalArgumentException("函数必须要有2个参数！");
		return String.format("(%1$s & %2$s)", arguments.get(0), arguments.get(1));
	}
}