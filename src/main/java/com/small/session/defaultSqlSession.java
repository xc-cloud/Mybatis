package com.small.session;

import java.lang.reflect.Proxy;
import java.util.List;

import com.small.binding.MappedProxy;
import com.small.config.Configuration;
import com.small.config.MappedStatement;
import com.small.executor.DefaultExecutor;
import com.small.executor.Executor;
/**
 * 对外提供数据访问API，例如select、update、delete、insert
 * 
 * @author super
 *
 */
public class defaultSqlSession implements SqlSession {
	
	private Configuration conf;
	private Executor executor;
	
	public defaultSqlSession(Configuration conf) {
		super();
		this.conf = conf;
		this.executor = new DefaultExecutor(conf);
	}

	@Override
	public Object selectOne(String statement, Object parameter) {
		MappedStatement mappedStatement = conf.getMappers().get(statement);
		List<Object> query = executor.query(mappedStatement, parameter);
		if(query == null ||query.size() == 0){
			return null;
		}else if(query.size()>0){
			throw new RuntimeException(" Too much data ！！！");
		}else 
			return query.get(0);
	}
	@Override
	public <E> List<E> selectList(String statement, Object parameter) {
		MappedStatement mappedStatement = conf.getMappers().get(statement);
		return executor.query(mappedStatement, parameter);
	}

	@Override
	public <T> T update(String statement, Object parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T delete(String statement, Object parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T insert(String statement, Object parameter) {
		// TODO Auto-generated method stub
		return null;
	}
	@SuppressWarnings("unchecked")
	public <T> T getMapper(Class<T> type){
		return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, new MappedProxy(this));
	}

}
