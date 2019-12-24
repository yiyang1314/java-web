package com.movebook.dao;

import java.util.List;

import com.movebook.entity.MoveBooking;


@SuppressWarnings("unused")
public interface MoveBookingDao {

	/**
	 * 查询全部
	 * @return
	 */
	public List<MoveBooking> findAll();
	
	/**
	 * 根据主键查对象
	 * @param MoveBooking
	 * @return
	 */
	public MoveBooking findById(Integer id);
	
	/**
	 * 模糊查询+分页
	 * @param column : 根据哪一列来模糊查询
	 * @param kw : 查询的关键字
	 * @param cp : 起始的行数
	 * @param ps : 每页最多显示多少行数据
	 * @return
	 */
	public List<MoveBooking> findPageAll(String column,String kw,int cp,int ps);
	
	/**
	 * 用来统计满足条件的数据总共有多少行
	 * @param column
	 * @param kw
	 * @return
	 */
	public int getCount(String column,String kw);
	
	/**
	 * 增加
	 * @param vo
	 * @return
	 */
	public boolean insert(MoveBooking h);
	
	/**
	 * 修改
	 * @param h : 对象里面，至少，必须有主键在
	 * @return
	 */
	public boolean update(MoveBooking h);
	
	/**
	 * 删除
	 * @param MoveBooking
	 * @return
	 */
	public boolean del(Integer id);
	
}
