package com.dao.test;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import com.renting.dao.HouseMapper;
import com.renting.db.MySqlSessionFactory;
import com.renting.entity.House;
import com.renting.entity.HouseStyle;
import com.renting.entity.TRegion;

public class HouseMapperTest {
	 private SqlSession sqlsession
		=MySqlSessionFactory.getMySqlSession();
	private HouseMapper dao = null;
	@Before
	public void init() {
		dao = sqlsession.getMapper(HouseMapper.class);
	}		
	
	
	@Test
	public void testFindAll() {
		dao.findAll().forEach(System.out::println);
	}

	@Test
	public void testFindById() {
		System.out.println(dao.findById(3));
	}

	@Test
	public void testFindPageAll() {
		Map<String, Object> map=new HashMap();
		map.put("title", "公");
		map.put("styleid", 2);
		
		map.put("minPrice", 35.5);
		map.put("maxPrice", 535.5);
		
		map.put("minArea", 35);
		map.put("maxArea", 535);
		
		map.put("upperRegion", "110101001");
		map.put("start",(3-1)*5);
		map.put("pageSize",5);
		dao.findPageAll(map).forEach(System.out::println);
		

	}

	
	
	@Test
	public void testSave() {
		House u=new House();
		u.setHouseArea(45);
		u.setHouseDate(new Date());
		u.setHousePrice(4576.9);
		HouseStyle houseStyle=new HouseStyle();
		houseStyle.setStyleid(4);
		u.setHouseStyle(houseStyle);
		TRegion region=new TRegion();
		region.setCode("110105004");
		region.setUpperRegion("110105");
		u.setRegion(region);
		u.setPhone("19878983476");
		u.setPublicDate(new Date());
		u.setTitle("易宝支付（YeePay.com）");
		u.setPicPath("dbnh");
		u.setSummary("易宝支付（YeePay.com）是中国行业支付的先行者，深耕行业支付和交易服务。易宝支付于2003年8月8日成立，总部位于北京，在上海、广东、江苏、福建、广西、天津、云南、四川、浙江、山东、陕西等设有30家分公司。自公司成立以来，易宝支付秉承成就客户、极致服务、实事求是、开放分享的核心价值观，以交易服务改变生活为使命，致力成为世界一流的交易服务平台，领跑电子支付、移动互联和互联网金融");
		System.out.println(u);
		System.out.println(dao.insert(u));
		sqlsession.commit();
	}
	
	

	@Test
	public void testUpdate() {
//		HousePlus u = dao.findById(3562);
//		System.out.println(u);
//		u.setName("wanbgggy");
//		System.out.println(dao.update(u));
	}

	@Test
	public void testDelete() {
		House u=new House();
		//System.out.println(dao.delete("3562"));
	}

	@Test
	public void testDeleteIds() {
//		String []ids={"2323","3582","c582","23","293","23","23"};
//		System.out.println(dao.deleteIds(ids));
	}

	@Test
	public void testGetCount() {
		Map<String, Object> map=new HashMap();
		
		map.put("title", "公");
		//map.put("styleid", 2);
		
		//map.put("minPrice", 35.5);
		//map.put("maxPrice", 535.5);
		
		//map.put("minArea", 35);
		//map.put("maxArea", 535);
		
		//map.put("upperRegion", "110101001");
		
		System.out.println(dao.getCount(map));
	}
	
	@Before
	public void destroy() {
		//MySqlSessionFactory.closeSqlSession();
	}
}
