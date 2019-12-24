package com.kdyzm.utils;

import com.renting.service.HouseService;
import com.renting.service.impl.HouseServiceImpl;

public class TestCase {

	public static void main(String[] args) {
		
		HouseService dao=new HouseServiceImpl();
		StudentHandler h=new StudentHandler(dao);
		HouseService proxy=(HouseService) h.getProxy();
		proxy.findAll();

	}

}
