package com.impl.test;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import com.renting.dao.HouseMapper;
import com.renting.db.MySqlSessionFactory;
import com.renting.service.BaseService;
import com.renting.service.UsersService;
import com.renting.service.impl.UsersServiceimpl;

public class UsersServiceimplTest  {
	 private BaseService service=null;
	@Before
	public void init() {
		service=new UsersServiceimpl();
	}	
	@Test
	public void testFindAll() {
		service.findAll().forEach(System.out::println);
	}

	@Test
	public void testFindById() {
		
	}

	@Test
	public void testFindPageAll() {
		
	}

	@Test
	public void testSave() {
		
	}

	@Test
	public void testUpdate() {
		
	}

	@Test
	public void testDelete() {
		
	}

	@Test
	public void testDeleteIds() {
		
	}

	@Test
	public void testGetCount() {
		
	}

}
