package com.small.config;

public class MappedStatement {
	private String namespace;
	private String Id;
	private String resultType;
	private String sql;
	public String getNamespace() {
		return namespace;
	}
	public MappedStatement setNamespace(String namespace) {
		this.namespace = namespace;
		return this;
	}
	public String getId() {
		return Id;
	}
	public MappedStatement setId(String id) {
		Id = id;
		return this;
	}
	public String getResultType() {
		return resultType;
	}
	public MappedStatement setResultType(String resultType) {
		this.resultType = resultType;
		return this;
	}
	public String getSql() {
		return sql;
	}
	public MappedStatement setSql(String sql) {
		this.sql = sql;
		return this;
	}
	
}
