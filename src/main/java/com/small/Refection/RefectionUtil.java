package com.small.Refection;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefectionUtil {
	public static void setBean(Object entity,ResultSet result) throws SQLException{
		Field[] declaredFields = entity.getClass().getDeclaredFields();//通过反射机制，获取对象的所有属性
		for(int i=0;i<declaredFields.length;i++){
			setValue(entity,declaredFields[i].getName(),result.getObject(declaredFields[i].getName()));
		}
	}
	private static void setValue(Object entity,String name,Object value){
		Field f;
		try {
			f = entity.getClass().getDeclaredField(name);//从entity对象中，获取name属性
			f.setAccessible(true);//设置该属性可以通过反射机访问
			f.set(entity, value);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
