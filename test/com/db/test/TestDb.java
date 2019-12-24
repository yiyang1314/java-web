package com.db.test;

import org.apache.ibatis.session.SqlSession;

import com.renting.dao.HouseMapper;
import com.renting.db.MySqlSessionFactory;

public class TestDb {

	public static void main(String[] args) {
		MySqlSessionFactory msf=new MySqlSessionFactory();
		SqlSession ss=msf.getMySqlSession();
		System.out.println(ss.selectList("com.renting.dao.HouseMapper.findAll"));
		HouseMapper dao=ss.getMapper(HouseMapper.class);
		System.out.println(dao.findAll());
		msf.closeSqlSession();

	}

}
