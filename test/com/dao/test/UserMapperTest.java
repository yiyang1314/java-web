package com.dao.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.renting.dao.UserMapper;
import com.renting.db.*;


public class UserMapperTest {
	 private SqlSession sqlsession
		=MySqlSessionFactory.getMySqlSession();
	private UserMapper dao = null;
	@Before
	public void init() {
		dao = sqlsession.getMapper(UserMapper.class);
	}	

	@Test
	public void testFindAll() {
		dao.findAll().forEach(System.out::println);
	}

	@Test
	public void testFindById() {
		System.out.println(dao.findById(1));
	}
	@Test
	public void testLogin() {
		Map<String, String> map=new HashMap();
		map.put("username", "zhangsan");
		map.put("password", "123");
		System.out.println(dao.login(map));
	}
	
	@After
	public void destroy() {
		//MySqlSessionFactory.closeSqlSession();
	}
}
