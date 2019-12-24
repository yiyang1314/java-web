package com.dao.test;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import com.renting.dao.TRegionMapper;

import com.renting.db.MySqlSessionFactory;

public class TRegionMapperTest {

	 private SqlSession sqlsession
		=MySqlSessionFactory.getMySqlSession();
	private TRegionMapper dao = null;
	@Before
	public void init() {
		dao = sqlsession.getMapper(TRegionMapper.class);
	}	

	@Test
	public void testFindAll() {
		dao.findAll().forEach(System.out::println);
	}
	
	@Test
	public void testFindBySupperId() {
		dao.findBySupperId(110102).forEach(System.out::println);
	}
	
	@Test
	public void testFindById() {
		System.out.println(dao.findById(110101006));
	}
	
}
