package com.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.movebook.dao.UsersDao;
import com.movebook.dao.impl.UsersDaoImpl;
import com.movebook.entity.Users;

public class UsersDaoImplTest {
	private UsersDao dao=null; 
	@Before
	public void init(){
		dao=new UsersDaoImpl(); 
	}

	@Test
	public void testFindAll() {
		dao.findAll().forEach(System.out::println);
	}

	@Test
	public void testFindById() {
		System.out.println(dao.findById(2222));
	}

	@Test
	public void testFindPageAll() {
		dao.findPageAll("name", "s", 1, 2).forEach(System.out::println);
	}

	@Test
	public void testGetCount() {
		System.out.println(dao.getCount("name", "s"));
	}

	@Test
	public void testUpdate() {
		Users move = dao.findById(2222);
		move.setName("james");;
		move.setPassword("222222");
		System.out.println(dao.update(move));
	}

	@Test
	public void testInsert() {
		Users user =new Users();
		user.setName("times");
		user.setPassword("3333444");
		System.out.println(dao.insert(user));
		
	}

	@Test
	public void testDel() {
		//System.out.println(dao.del(2222));
	}
	
	@Test
	public void testLogin() {
		System.out.println(dao.login("zhangsan", "123456"));
	}
}
