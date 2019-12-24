package com.renting.service;

import java.util.List;

import com.renting.entity.TRegion;

public interface TRegionService extends BaseService<TRegion> {
	List<TRegion> findBySupperId(Integer region);
}
