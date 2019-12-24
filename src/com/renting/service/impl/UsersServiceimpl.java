package com.renting.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.renting.dao.UserMapper;
import com.renting.db.MySqlSessionFactory;
import com.renting.entity.Users;
import com.renting.entity.Result;
import com.renting.entity.Users;

import com.renting.service.UsersService;

public class UsersServiceimpl implements UsersService {
	private SqlSession sqlsession=MySqlSessionFactory.getMySqlSession();
	private UserMapper dao=sqlsession.getMapper(UserMapper.class);
	
	
	@Override
	public List<Users> findAll() {
		return dao.findAll();
	}

	@Override
	public Users findById(Integer id) {
		
		return dao.findById(id);
	}

	

	@Override
	public Result save(Users user) {
		Result rst=new Result();
		boolean flags=dao.insert(user);
		if(flags){
			rst.setStatus(200);
			rst.setMsg("保存成功！");
			sqlsession.commit();
			return rst;
		}
		rst.setStatus(405);
		rst.setMsg("添加失败！");
		sqlsession.rollback();
		return rst;
	}

	@Override
	public Result update(Users entity) {
		Result rst=new Result();
		boolean flags=dao.update(entity);
		if(flags){
			rst.setStatus(200);
			rst.setMsg("更新成功！");
			sqlsession.commit();
			return rst;
		}
		rst.setStatus(405);
		rst.setMsg("更新失败！");
		sqlsession.rollback();
		return rst;
	}

	@Override
	public Result delete(Users u) {
		Result rst=new Result();
		Integer idd=-1;
		boolean flags=dao.delete(u);
		if(flags){
			rst.setStatus(200);
			rst.setMsg("更新成功！");
			sqlsession.commit();
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
			sqlsession.commit();
			return rst;
		}
		rst.setStatus(405);
		rst.setMsg("删除失败！");
		sqlsession.rollback();
		return rst;
	}



	@Override
	public Result login(String username, String password) {
		Result rst=new Result();
		Map<String,String> mp=new HashMap<String,String>();
		mp.put("username", username);
		mp.put("password", password);
		Users u = dao.login(mp);
		if(u==null){
			rst.setStatus(405);
			rst.setMsg("用户名或密码错误，登录失败！");
			return rst;
		}
		rst.setStatus(200);
		rst.setMsg("登陆成功！");
		rst.setData(u);
		return rst;
	}
}
