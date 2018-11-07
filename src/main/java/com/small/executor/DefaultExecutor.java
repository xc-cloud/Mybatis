package com.small.executor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.small.Refection.RefectionUtil;
import com.small.config.Configuration;
import com.small.config.MappedStatement;

public class DefaultExecutor implements Executor{
	private Configuration conf;
	private Map<String,Object> s = new HashMap<>();
	public DefaultExecutor(Configuration conf) {
		super();
		this.conf = conf;
	}

	@Override
	public <E> List<E> query(MappedStatement mappedStatement, Object parameter) {
		List<E> result = new ArrayList<E>();
		try {
			Class.forName(conf.getDriver());//加载驱动
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet resultSet = null;
		//获取JDBC连接
		try {
			conn = DriverManager.getConnection(conf.getUrl(),conf.getUsername(),conf.getPassword());
			prep = conn.prepareStatement(mappedStatement.getSql());
//			prep.setObject(parameterIndex, x);
			//:TODO 缺少参数传递
			
			//防止sql注入
			resultSet = prep.executeQuery();//查询出结果
			//将结果通过反射机制封装到bean类
			handlerResultSet(resultSet,result,mappedStatement.getResultType());
			resultSet.close();
			prep.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	private <E> void handlerResultSet(ResultSet result,List<E> ret,String classname){
		Class<E> clazz = null;
		try {
			clazz = (Class<E>) Class.forName(classname);//根据反射机制，获取对象
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(result.next()){//循环遍历结果集
				Object entity = clazz.newInstance();//创建一个新的对象
				RefectionUtil.setBean(entity,result);
				ret.add((E) entity);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
//		System.out.println(((Object)"A").hashCode());
//		System.out.println(((Object)"B").hashCode());
//		System.out.println(((Object)"C").hashCode());
//		System.out.println(((Object)"AB").hashCode());
//		System.out.println(((Object)"BC").hashCode());
//		System.out.println(((Object)"AC").hashCode());
//		System.out.println(((Object)"ABC").hashCode());
//		String s = "如果说发生纠纷刘索拉附件是";
//		System.out.println(s.hashCode());
//		System.out.println(s.hashCode()>>>16);
//		System.out.println(s.hashCode()^(s.hashCode()>>>16));
		Map<String,Object> m = new HashMap<String,Object>();
		System.out.println(m.put("如果说发生纠纷刘索拉附件是", "如果说发生纠纷刘索拉附件是"));
	}
}
