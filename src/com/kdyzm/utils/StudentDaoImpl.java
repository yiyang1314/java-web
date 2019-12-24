package com.kdyzm.utils;

public class StudentDaoImpl implements StudentDao {


	public void show(int age, String name) {
		System.out.println("我的年龄："+age+" 姓名："+name);
		
	}


	public void active(String name) {
		System.out.println("我的姓名："+name);
	}
	
	
	

}
