package com.renting.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.renting.dao.HouseStyleMapper;
import com.renting.db.MySqlSessionFactory;
import com.renting.entity.HouseStyle;
import com.renting.entity.Result;
import com.renting.service.HouseStyleService;

public class HouseStyleServiceImpl implements HouseStyleService {

	private SqlSession sqlsession=MySqlSessionFactory.getMySqlSession();
	private HouseStyleMapper dao=sqlsession.getMapper(HouseStyleMapper.class);
	
	
	@Override
	public List<HouseStyle> findAll() {
		return dao.findAll();
	}

	@Override
	public HouseStyle findById(Integer id) {
		
		return dao.findById(id);
	}



	@Override
	public Result save(HouseStyle entity) {
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

	@Override
	public Result update(HouseStyle entity) {
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

	@Override
	public Result delete(HouseStyle entity ) {
		Result rst=new Result();
		boolean flags=dao.delete(entity);
		if(flags){
			rst.setStatus(200);
			rst.setMsg("更新成功！");
			return rst;
		}
		rst.setStatus(405);
		rst.setMsg("更新失败！");
		return rst;
	}

	@Override
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


}
