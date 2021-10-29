package com.cab.service;

import java.util.List;

import com.cab.vo.CitiesVo;

public interface CitiesService {

	void save(CitiesVo citiesVo) ;
	void update(CitiesVo citiesVo) ;
	void delete(Integer id);
	CitiesVo get(Integer id);
	List<CitiesVo> getAll();

}

