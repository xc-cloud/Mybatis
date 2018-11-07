package com.small.binding;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;

import com.small.session.SqlSession;

public class MappedProxy implements InvocationHandler {
	private SqlSession sqlsession;
	
	public MappedProxy(SqlSession sqlsession) {
		super();
		this.sqlsession = sqlsession;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Class<?> returnType = method.getReturnType();
		System.out.println("传入参数："+args[0]);
		if(Collection.class.isAssignableFrom(returnType)){
			return sqlsession.selectList(method.getDeclaringClass().getName()+"."+method.getName(), args == null ? null : args[0]);
		}else{
			return sqlsession.selectOne(method.getDeclaringClass().getName()+"."+method.getName(), args == null ? null : args[0]);	
		}
	}

}
