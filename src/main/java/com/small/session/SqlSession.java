package com.small.session;

import java.util.List;

public interface SqlSession {
	<T> T selectOne(String statement,Object parameter);
	<E> List<E> selectList(String statement,Object parameter);
	<T> T update(String statement,Object parameter);
	<T> T delete(String statement,Object parameter);
	<T> T insert(String statement,Object parameter);
	<T> T getMapper(Class<T> type);
}
