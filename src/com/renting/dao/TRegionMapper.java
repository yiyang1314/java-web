package com.renting.dao;

import java.util.List;

import com.renting.entity.TRegion;

public interface TRegionMapper extends BaseMapper<TRegion> {
	List<TRegion> findBySupperId(Integer region);
}
