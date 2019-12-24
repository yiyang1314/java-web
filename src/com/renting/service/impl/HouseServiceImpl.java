package com.renting.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.renting.dao.BaseMapper;
import com.renting.dao.HouseMapper;
import com.renting.db.MySqlSessionFactory;
import com.renting.entity.House;
import com.renting.entity.Result;
import com.renting.service.BaseService;
import com.renting.service.HouseService;

public class HouseServiceImpl implements HouseService {
	private SqlSession sqlsession=MySqlSessionFactory.getMySqlSession();
	private HouseMapper dao=sqlsession.getMapper(HouseMapper.class);
	
	
	@Override
	public List<House> findAll() {
		List<House> list=null;
		try {
			list=dao.findAll();
		} catch (Exception e) {
			System.out.println(e.getMessage()+": "+e.getCause());
			list=Collections.EMPTY_LIST;
		}
		return list;
	}

	@Override
	public House findById(Integer id) {
		House house=null;
		try {
			house= dao.findById(id);
		} catch (Exception e) {
			System.out.println(e.getMessage()+": "+e.getCause()+": "+e.getCause());
			
		}
		return house;
	}


	@Override
	public Result save(House h) {
		Result rst=new Result();
		try {
			boolean flags=dao.insert(h);
			rst.setStatus(200);
			rst.setMsg("保存成功！");
			sqlsession.commit();
			return rst;
		} catch (Exception e) {
			System.out.println(e.getMessage()+": "+e.getCause());
			rst.setStatus(405);
			rst.setMsg("添加失败！");
			sqlsession.rollback();
		}
		return rst;
	}

	@Override
	public Result update(House entity) {
		Result rst=new Result();
		try {
			boolean flags=dao.update(entity);
			if(flags){
				rst.setStatus(200);
				rst.setMsg("更新成功！");
				sqlsession.commit();
				return rst;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()+": "+e.getCause());
			rst.setStatus(405);
			rst.setMsg("更新失败！");
			sqlsession.rollback();
		}
		return rst;
	}

	@Override
	public Result delete(House entity) {
		Result rst=new Result();
		try {
			boolean flags=dao.delete(entity);
			if(flags){
				sqlsession.commit();
				rst.setStatus(200);
				rst.setMsg("删除成功！");
				return rst;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()+": "+e.getCause());
			rst.setStatus(405);
			rst.setMsg("删除失败！");
			sqlsession.rollback();
		}
		return rst;

	}

	@Override
	public Result deleteIds(String[] ids) {
		Result rst=new Result();
		Integer idd=null;
		try{
			List<Integer> list=new ArrayList<Integer>();
			for(String id: ids){
				if(id!=null&&id.length()>0){
					Integer.valueOf(id);
				}
			}
			int count=dao.delBatchIds(list);
			if(count>0){
				rst.setStatus(200);
				rst.setMsg("共删除"+count+"条记录！");
				sqlsession.commit();
				return rst;
			}
		}catch(Exception e){
			System.out.println(e.getMessage()+": "+e.getCause());
		}
		rst.setStatus(405);
		rst.setMsg("删除失败！");
		sqlsession.rollback();
		return rst;
	}

	@Override
	public int getCount(Map<String,Object> map) {
		try {
			return dao.getCount(map);
		} catch (Exception e) {
			System.out.println(e.getMessage()+": "+e.getCause());
			
		}
		return 0;
	}

	@Override
	public List<House> findPageAll(Map<String, Object> map) {
		List<House> list=null;
		try {
			list= dao.findPageAll(map);
		} catch (Exception e) {
			System.out.println(e.getMessage()+": "+e.getCause());
			list=Collections.EMPTY_LIST;
		}
		return list;
	}


}
