package com.small;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import com.small.mapper.UserMapper;
import com.small.pojo.Users;
import com.small.session.SqlSession;
import com.small.session.SqlSessionFactory;

public class Snippet {

	public static void main(String[] args) {
//		SqlSessionFactory sqlSessionFactory = new SqlSessionFactory();
//		SqlSession openSession = sqlSessionFactory.openSession();
//		UserMapper mapper = openSession.getMapper(UserMapper.class);
//		Users users = new Users();
//		users.setUsername("你好");
//		users.setPassword("哈哈");
//		List<Users> select = mapper.select(users);
//		for (Users user : select) {
//			System.out.println("用户名：" + user.getUsername() + "密码：" + user.getPassword()+"年龄：" + user.getAge() + "金钱：" + user.getMoney()+"创建日期：" + user.getTime());
//		}
		try {
			Class<?> obj = Class.forName("com.small.binding.MappedProxy");
			Method[] methods = obj.getMethods();
			Field[] declaredFields = obj.getDeclaredFields();
			for(Method temp : methods){
				System.out.println(temp.getName());
			}
			System.out.println("----");
			System.out.println("----");
			for(Field temp : declaredFields){
				System.out.println(temp.getName());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Map<String, MappedStatement> mappers =
		// sqlSessionFactory.conf.getMappers();
		// MappedStatement mappedStatement =
		// mappers.get("com.small.mapper.UserMapper.select");
		// System.out.println(mappedStatement.getId());
		// System.out.println(mappedStatement.getNamespace());
		// System.out.println(mappedStatement.getResultType());
		// System.out.println(mappedStatement.getSql());
	}
}
