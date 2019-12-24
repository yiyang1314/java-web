package com.renting.dao;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import com.renting.db.MySqlSessionFactory;

public class HouseStyleMapperTest{
	 private SqlSession sqlsession
		=MySqlSessionFactory.getMySqlSession();
	private HouseStyleMapper dao = null;
	@Before
	public void init() {
		dao = sqlsession.getMapper(HouseStyleMapper.class);
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
	public void testFindPageAll() {
		//System.out.println(dao.findPageAll("dname", "", 1, 3).size());

	}


}
