package com.renting.service;

import java.util.List;
import java.util.Map;

import com.renting.entity.House;

public interface HouseService extends BaseService<House> {

	/**
	 * 模糊查询+分页
	 * @param column : 根据哪一列来模糊查询
	 * @param keywords : 查询的关键字
	 * @param currentPage : 起始的行数
	 * @param pageSize : 每页最多显示多少行数据
	 * @return
	 */
	public List<House> findPageAll(Map<String ,Object> map);
	
	
	public int getCount(Map<String ,Object> map);
	
}
