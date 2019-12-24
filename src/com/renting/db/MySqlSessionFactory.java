package com.renting.db;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MySqlSessionFactory {
	private static final String RESOURCE = "mybatis_config.xml";
	private static SqlSessionFactoryBuilder builder = null;
	private static SqlSessionFactory factory = null;
	private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
	static{
		try {
			InputStream is = Resources.getResourceAsStream(RESOURCE);
			builder = new SqlSessionFactoryBuilder();
			factory = builder.build(is);
		} catch (IOException e) {
			System.out.println("加载配置文件.....");
		}
	}
	
	
	public static SqlSession getMySqlSession() {
		SqlSession sqlSession = threadLocal.get();
		if(sqlSession==null) {
			sqlSession = factory.openSession();
			threadLocal.set(sqlSession);
		}
		return sqlSession;
	}
	public static void closeSqlSession() {
		SqlSession sqlSession = threadLocal.get();
		if(sqlSession!=null) {
			sqlSession.close();
		}
		threadLocal.set(null);
	}
	
}
