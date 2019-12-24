package com.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.movebook.dao.MoveBookingDao;
import com.movebook.dao.impl.MoveBookingDaoImpl;
import com.movebook.entity.MoveBooking;



public class MoveBookingDaoImplTest {
	private MoveBookingDao dao=null; 
	@Before
	public void init(){
		dao=new MoveBookingDaoImpl(); 
	}
	@Test
	public void testFindAll() {
		dao.findAll().forEach(System.out::println);
	}

	@Test
	public void testFindById() {
		System.out.println(dao.findById(3332));
	}

	@Test
	public void testFindPageAll() {
		dao.findPageAll("area", "区", 2, 2).forEach(System.out::println);
	}

	@Test
	public void testGetCount() {
		System.out.println(dao.getCount("area", "东城区"));
	}

	@Test
	public void testUpdate() {
		MoveBooking move = dao.findById(4442);
		move.setContact("王二");
		move.setMovedate(new Date());
		System.out.println(dao.update(move));
	}

	@Test
	public void testInsert() {
		MoveBooking move = new MoveBooking();
		move.setArea("西城区");
		move.setCartype("厢式小货");
		move.setMovedate(new Date());
		move.setContact("张三");
		move.setPhone("18267898765");
		move.setStatus("0");
		System.out.println(dao.insert(move));
		
	}

	@Test
	public void testDel() {
		System.out.println(dao.del(4442));
	}

}
