package com.cab.service;

import java.util.List;

import com.cab.vo.TripsVo;

public interface TripsService {

	void save(TripsVo tripsVo) ;
	void update(TripsVo tripsVo) ;
	void delete(Integer id);
	TripsVo get(Integer id);
	List<TripsVo> getAll();

}

