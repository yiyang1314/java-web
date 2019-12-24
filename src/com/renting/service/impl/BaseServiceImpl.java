package com.renting.service.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.renting.dao.BaseMapper;
import com.renting.db.MySqlSessionFactory;
import com.renting.entity.Result;
import com.renting.service.BaseService;

public class BaseServiceImpl<T,E> implements BaseService<T> {

	private BaseMapper dao =null;
	
	public BaseServiceImpl(){
		Type[] type=this.getClass().getTypeParameters();
	//	dao=(Class<BaseMapper>) type[1];
		
	}
	public List<T> findAll() {
		return dao.findAll();
	}

	
	public T findById(Integer id) {
		
		return (T) dao.findById(id);
	}

	
	public List<T> findPageAll(String column, String keywords, Integer currentPage, Integer pageSize) {
		Map<String,Object> mp=new HashMap<String,Object>();
		mp.put("column", column);
		mp.put("keywords", keywords);
		mp.put("currentPage", currentPage);
		mp.put("pageSize", pageSize);
		return dao.findPageAll(mp);
	}

	
	public Result save(T entity) {
		Result rst=new Result();
		boolean flags=dao.insert(entity);
		if(flags){
			rst.setStatus(200);
			rst.setMsg("保存成功！");
			return rst;
		}
		rst.setStatus(405);
		rst.setMsg("添加失败！");
		return rst;
	}

	
	public Result update(T entity) {
		Result rst=new Result();
		boolean flags=dao.update(entity);
		if(flags){
			rst.setStatus(200);
			rst.setMsg("更新成功！");
			return rst;
		}
		rst.setStatus(405);
		rst.setMsg("更新失败！");
		return rst;
	}

	
	public Result delete(T t) {
		Result rst=new Result();
		boolean flags=dao.delete(t);
		if(flags){
			rst.setStatus(200);
			rst.setMsg("更新成功！");
			return rst;
		}
		rst.setStatus(405);
		rst.setMsg("更新失败！");
		return rst;
	}

	
	public Result deleteIds(String[] ids) {
		Result rst=new Result();
		Integer idd=null;
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
			return rst;
		}
		rst.setStatus(405);
		rst.setMsg("删除失败！");
		return rst;
	}

	
	public int getCount(String column, String keywords) {
		Map<String,String> mp=new HashMap<String,String>();
		mp.put("column", column);
		mp.put("keywords", keywords);
		return dao.getCount(mp);
	}

}
