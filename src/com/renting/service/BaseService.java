package com.renting.service;

import java.util.List;
import java.util.Map;

import com.renting.entity.Result;



public interface BaseService<T> {
	
	/**
	 * 查询全部
	 * @return
	 */

	public List<T> findAll();
	
	/**
	 * 根据主键查对象
	 * @param Tno
	 * @return
	 */
	public T findById(Integer id);
	

	
	/**
	 * 增加
	 * @param vo
	 * @return
	 */

	public Result save(T entity);
	
	/**
	 * 修改
	 * @param vo : 对象里面，至少，必须有主键在
	 * @return
	 */
	public Result update(T entity);
	
	/**
	 * 删除
	 * @param T
	 * @return
	 */

	public Result delete(T entity);

	/**deleteIds
	 * 根据ids 批量删除
	 * @param request
	 * @param response
	 */
	public Result deleteIds(String[] ids);
	
	/**
	 * 用来统计满足条件的数据总共有多少行
	 * @param column
	 * @param kw
	 * @return
	 */
	
}
