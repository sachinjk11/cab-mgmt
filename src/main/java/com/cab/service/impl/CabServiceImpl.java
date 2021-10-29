package com.cab.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.dao.CabDao;
import com.cab.dao.TripsDao;
import com.cab.dao.TripsDao.Mapping;
import com.cab.entity.Cab;
import com.cab.entity.Trips;
import com.cab.exception.NoCabFoundException;
import com.cab.exception.NoOngoingTripException;
import com.cab.service.CabService;
import com.cab.vo.CabVo;
import com.cab.vo.TripsVo;

@Service
public class CabServiceImpl implements CabService {

	@Autowired
	CabDao cabDao;
	
	@Autowired
	TripsDao tripDao;

	@Override
	@Transactional
	public void save(CabVo cabVo) {	
		Integer id = cabVo.getId();
		Boolean objectAlreadyExists=cabDao.existsById(id);
		if(!objectAlreadyExists) {
			Cab cab = new Cab();
			BeanUtils.copyProperties(cabVo, cab);
			cabDao.save(cab);
			addWaitEntry(cab.getId(), cab.getCitiyId());
		}else {
			throw new EntityExistsException();
		}
		
	}

	private void addWaitEntry(Integer cabId,Integer cityId) {
		Trips t = new Trips();
		t.setCabId(cabId);
		t.setCityId(cityId);
		t.setStartTime(new Date());
		t.setStatus(0);
		tripDao.save(t);
	}
	
	@Override
	@Transactional
	public void update(CabVo cabVo) {
		Integer id = cabVo.getId();
		Boolean objectExists=cabDao.existsById(id);
		if(objectExists) {
			Cab cab = new Cab();
			BeanUtils.copyProperties(cabVo, cab);
			cabDao.save(cab);
		}else {
			throw new EntityNotFoundException();
		}
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		Boolean objectExists=cabDao.existsById(id);
		if(objectExists) {
			cabDao.deleteById(id);
		}else {
			throw new EntityNotFoundException();
		}
	}

	@Override
	@Transactional
	public CabVo get(Integer id) {
		Optional<Cab> cabOptional = cabDao.findById(id);
		CabVo cabVo=null;
		if(cabOptional.isPresent()) {
			cabVo = new CabVo();
			BeanUtils.copyProperties(cabOptional.get(), cabVo);	
		}else {
			throw new EntityNotFoundException();
		}
		
		return cabVo;
	}

	@Override
	@Transactional
	public List<CabVo> getAll() {
		List<Cab> cabList = cabDao.findAll();
		List<CabVo> cabVoList = new ArrayList<>();
		if (cabList != null && !cabList.isEmpty()) {
			for (Cab cab : cabList) {
				CabVo cabVo = new CabVo();
				BeanUtils.copyProperties(cab, cabVo);
				cabVoList.add(cabVo);
			}
		}
		return cabVoList;
	}

	@Override
	public TripsVo bookRide(Integer cityId) throws NoCabFoundException {
		//ideal time
		LinkedList<Mapping> waitTimeData = tripDao.getMappingData();
		for(Mapping data : waitTimeData) {
			
			//ongoing ride
			Trips ontrip = tripDao.getOngoingRide(data.getCabId());
			Trips offtrip = tripDao.getWaitRide(data.getCabId());
			//bookcab - add entry in trip
			if(ontrip == null && offtrip != null) {
				
				offtrip.setEndTime(new Date());
				tripDao.save(offtrip);
				
				Trips newtrip = new Trips();
				newtrip.setCabId(data.getCabId());
				newtrip.setCityId(cityId);
				newtrip.setStartTime(new Date());
				newtrip.setStatus(1);
				tripDao.save(newtrip);
				
				TripsVo tripsVo = new TripsVo();
				BeanUtils.copyProperties(newtrip, tripsVo);	
				return tripsVo;
			}
		}
		throw new NoCabFoundException("All cab is busy. Please wait for sometime");
	}

	@Override
	public TripsVo CompleteRide(Integer cabId) throws NoOngoingTripException {

		Trips trip = tripDao.getOngoingRide(cabId);
		if(trip != null) {
			
			trip.setEndTime(new Date());
			tripDao.save(trip);
			
			addWaitEntry(cabId, trip.getCityId());
			
			TripsVo tripsVo = new TripsVo();
			BeanUtils.copyProperties(trip, tripsVo);	
			return tripsVo;
		}
		throw new NoOngoingTripException("All Ongoing trips are ended. no more active ongoing present");
	}

}

