package com.movebook.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.movebook.dao.UsersDao;
import com.movebook.dao.impl.UsersDaoImpl;

import com.movebook.entity.Users;

import com.movebook.service.UsersService;

public class UsersServiceImpl implements UsersService {
	private UsersDao dao = new UsersDaoImpl();
	
	/**
	 * 查询全部
	 * @return
	 */
	public List<Users> findAll(){
		
		List<Users> list=new ArrayList<Users>();;
		try {
			 list =dao.findAll();
		} catch (Exception e) {
			System.out.println("findAll'查询列表失败！");
			list =Collections.EMPTY_LIST;
		}
		return list;
	}
	
	/**
	 * 根据主键查对象
	 * @param Usersno
	 * @return
	 */
	public Users findById(Integer Usersid){
		
		Users Users=null;
		try {
			Users=dao.findById(Usersid);
		} catch (Exception e) {
			System.out.println("findById'查询列表失败！");
		}
		return Users;
	}
	
	/**
	 * 模糊查询+分页
	 * @param column : 根据哪一列来模糊查询
	 * @param kw : 查询的关键字
	 * @param cp : 起始的行数
	 * @param ps : 每页最多显示多少行数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Users> findPageAll(String column,String keywords,Integer currentPage,Integer pageSize){
		
		try {
			String col="title";
			String key="";
			if(column!=null){
				col=column;
			}
			if(keywords!=null){
				key=keywords;
			}
			List<Users> list =dao.findPageAll(
					col, 
					key, 
					currentPage, 
					pageSize);

			return list;
		} catch (Exception e) {
			System.out.println("findPageAll'查询分页列表失败！");
		}
		return Collections.EMPTY_LIST;
	}
	
	
	/**
	 * 增加
	 * @param vo
	 * @return
	 */

	public Map save(Users Users){
		Map map=new HashMap();
		try {
			if (dao.insert(Users)) {
				map.put("status", 200);
				map.put("msg", "添加成功!");
				return map;
			}
		}catch (Exception e) {
			System.out.println("save'注册失败！");
		}
		
		map.put("status", 404);
		map.put("msg", "添加失败!");
		return map;
	}
	
	/**
	 * 修改
	 * @param vo : 对象里面，至少，必须有主键在
	 * @return
	 */
	public Map update(Users Users){
		Map map=new HashMap();
		try {
			if(dao.update(Users)){
				map.put("status", 200);
				map.put("msg", "修改成功!");
				return map;
			}
		
		}catch (Exception e) {
			System.out.println("update'更新列表失败！");
		}
		map.put("status", 404);
		map.put("msg", "修改失败!");
		return map;
	}
	
	/**
	 * 删除
	 * @param Usersno
	 * @return
	 */

	public Map delete(String id){
		Map map=new HashMap();
		try {
			if(id==null){
				map.put("status", 302);
				map.put("msg", "房源不存在！");
				return map;
			}
			if(dao.del(Integer.parseInt(id))){
				map.put("status", 200);
				map.put("msg", "删除成功！");
				return map;
			}
		} catch (Exception e) {
			System.out.println("delete'删除失败！");
		}
		map.put("status", 404);
		map.put("msg", "删除失败！");
		return map;

		
	}

	/**deleteIds
	 * 根据ids 批量删除
	 * @param request
	 * @param response
	 */
	public Map deleteIds(String[] ids){
		Map map=new HashMap();
		int n=0;
		try {
			
			for(String id:ids){
				System.out.println(id);
				dao.del(Integer.parseInt(id));
				n++;
			}
			if(n>0){
				map.put("status", 200);
				map.put("msg", "已批量删除"+n+ "条记录！");
				return map;
			}
		} catch (Exception e) {
			System.out.println("deleteIds'删除列表失败！");
		}
		map.put("status", 404);
		map.put("msg", "批量删除失败！");
		return map;
	}
	
	/**
	 * 用来统计满足条件的数据总共有多少行
	 * @param column
	 * @param kw
	 * @return
	 */
	public int getCount(String column,String keywords){
		try{
			String col="title";
			String key="";
			if(column!=null){
				col=column;
			}
			if(keywords!=null){
				key=keywords;
			}
			
			return dao.getCount(column, key);
		} catch (Exception e) {
				System.out.println("deleteIds'删除列表失败！");
		}
		return 0;
	}

	/**
	 * 登录
	 * @param username 用户名
	 * @param password 密码
	 * @return  true|false登录成功或者失败
	 */
	public Map login(String  username,String password){
		Map map=new HashMap();
		Users u=dao.login(username, password);
		if(u!=null){
			map.put("status", 200);
			map.put("msg", "登录成功!");
			map.put("user", u);
			return map;
		}
		
		map.put("status", 404);
		map.put("msg", "登录失败，请稍后重试!");
		return map;
	}

}
