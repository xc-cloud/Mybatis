package com.small.session;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.small.config.Configuration;
import com.small.config.MappedStatement;
import com.small.filter.SmallFileFilter;
import com.small.mapper.UserMapper;
import com.small.pojo.Users;

/**
 * 1、加载配置信息 2、创建sqlSession
 */
public class SqlSessionFactory {
	private Configuration conf = new Configuration();

	public SqlSessionFactory() {
		loadDbInfo();
		loadMappersInfo();
	}

	private void loadDbInfo() {
		InputStream resourceAsStream = SqlSessionFactory.class.getClassLoader().getResourceAsStream("db.properties");
		Properties properties = new Properties();
		try {
			properties.load(resourceAsStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conf.setDriver(properties.getProperty("jdbc.driver"));
		conf.setUrl(properties.getProperty("jdbc.url"));
		conf.setUsername(properties.getProperty("jdbc.username"));
		conf.setPassword(properties.getProperty("jdbc.password"));
	}

	private void loadMappersInfo() {
		URL resource = SqlSessionFactory.class.getClassLoader().getResource("com/small/mapper");
		File file = new File(resource.getFile());
		if (file.isDirectory()) {
			FileFilter fileFilter = new SmallFileFilter();
			File[] listFiles = file.listFiles(fileFilter);
			for (File temp : listFiles) {
				loadMapperInfo(temp);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void loadMapperInfo(File file) {
		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			document = saxReader.read(file);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element rootElement = document.getRootElement();// 得到mapper文件中mappers节点
		String namespace = rootElement.attributeValue("namespace");
		List<Element> selects = rootElement.elements();// 获取所有的子节点对象包括常见的select、update、delete、insert，如果需要获取特定的，可以通过传入字符串获取
		for (Element select : selects) {
			MappedStatement mappedStatement = new MappedStatement();
			mappedStatement.setId(select.attributeValue("id"));
			mappedStatement.setResultType(select.attributeValue("resultType"));
			mappedStatement.setSql(select.getTextTrim());
			mappedStatement.setNamespace(namespace + "." + mappedStatement.getId());
			conf.getMappers().put(namespace + "." + mappedStatement.getId(), mappedStatement);
		}

	}
	public SqlSession openSession(){
		return new defaultSqlSession(conf);
	}
}
