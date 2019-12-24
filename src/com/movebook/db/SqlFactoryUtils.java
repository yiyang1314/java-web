package com.movebook.db;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;



public class SqlFactoryUtils {
	private static String config="/mybatis.xml";
	private static SqlSessionFactoryBuilder build=null;
	private static SqlSessionFactory factory=null;
	public  static ThreadLocal<SqlSession> session=new ThreadLocal<SqlSession>();
	
	static{
		//指定mybatis.xml文件的名字
		
		//加载mybatis配置文件，用于读取mybatis.xml
		InputStream ins=SqlFactoryUtils.class.getResourceAsStream(config);
		//创建构造模式，拿到构造器
		build=new SqlSessionFactoryBuilder();
		//读取，并解析MYBATIS的配置，创建Configration对象，配置数据源，事务管理，映射文件，
		//获得sql工厂
		factory=build.build(ins);
		//通过工厂与数据库建立连接，获取连接对象session
	}
	
	public static void closeSqlSesson(String[] args) {
		SqlSession ss=session.get();
		if(ss!=null){
			ss.close();
			session.set(null);
			
		}

	}
	public static SqlSession getSqlSession() {
		
		SqlSession ss=session.get();
		if(ss==null){
			ss=factory.openSession();
			session.set(ss);
			
		}
		return ss;
	}
	public static void main(String[] args) {
		

	}

}
