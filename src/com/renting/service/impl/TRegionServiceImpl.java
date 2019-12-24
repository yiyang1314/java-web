package com.renting.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.renting.dao.TRegionMapper;
import com.renting.db.MySqlSessionFactory;
import com.renting.entity.TRegion;
import com.renting.entity.Result;
import com.renting.entity.TRegion;
import com.renting.service.BaseService;
import com.renting.service.TRegionService;

public class TRegionServiceImpl implements TRegionService {
	private SqlSession sqlsession=MySqlSessionFactory.getMySqlSession();
	private TRegionMapper dao=sqlsession.getMapper(TRegionMapper.class);
	
	
	@Override
	public List<TRegion> findAll() {
		return dao.findAll();
	}

	@Override
	public TRegion findById(Integer id) {
		
		return dao.findById(id);
	}

	
	@Override
	public Result save(TRegion entity) {
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
	public Result update(TRegion entity) {
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
	public Result delete(TRegion entity ) {
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



	@Override
	public List<TRegion> findBySupperId(Integer region) {
		
		return dao.findBySupperId(region);
	}


}
