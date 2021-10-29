package com.cab.service;

import java.util.List;

import com.cab.exception.NoCabFoundException;
import com.cab.exception.NoOngoingTripException;
import com.cab.vo.CabVo;
import com.cab.vo.TripsVo;

public interface CabService {

	void save(CabVo cabVo) ;
	void update(CabVo cabVo) ;
	void delete(Integer id);
	CabVo get(Integer id);
	List<CabVo> getAll();
	TripsVo bookRide(Integer cityId) throws NoCabFoundException;
	TripsVo CompleteRide(Integer cabId) throws NoOngoingTripException;

}

