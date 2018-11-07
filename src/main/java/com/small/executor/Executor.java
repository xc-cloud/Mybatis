package com.small.executor;

import java.util.List;

import com.small.config.MappedStatement;

public interface Executor {
	public <T> List<T> query(MappedStatement mappedStatement,Object parameter);
}
