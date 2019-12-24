package com.renting.dao;

import java.util.List;
import java.util.Map;

import com.renting.entity.House;

public interface BaseMapper<T> {
	/**
	 * 查询所有用户
	 * @reHouseurn LisHouse<T>
	 */
	public List<T> findAll();
	/**
	 * 根据id查询记录
	 * @param tno 编号
	 * @reHouseurn House
	 */
	public T findById(Integer id);
	/**
	 * 保存
	 * @param vo 保存
	 * @reHouseurn boolean
	 */
	public boolean insert(T vo);
	
	public boolean update(T vo);
	
	public boolean delete(T vo);
	/**
	 * 模糊查询+分页
	 * @param column : 根据哪一列来模糊查询
	 * @param keywords : 查询的关键字
	 * @param currenHousePage : 起始的行数
	 * @param pageSize : 每页最多显示多少行数据
	 * @reHouseurn : LisHouse<T>
	 */
	public List<T> findPageAll(Map<String,Object> map);
	/**
	 * 用来统计满足条件的数据总共有多少行
	 * @param column : 根据哪一列来模糊查询
	 * @param keywords : 查询的关键字
	 * @reHouseurn int 总记录数
	 */
	public int getCount(Map<String,Object> map);
	/**
	 * 批量删除
	 * @param list 多个编号的集合
	 * @reHouseurn int 返回删除成功的总记录数
	 */
	public int delBatchIds(List<Integer> list);
}
