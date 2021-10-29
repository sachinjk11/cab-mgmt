package com.cab.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.dao.TripsDao;
import com.cab.entity.Trips;
import com.cab.service.TripsService;
import com.cab.vo.TripsVo;

@Service
public class TripsServiceImpl implements TripsService {

	@Autowired
	TripsDao tripsDao;

	@Override
	@Transactional
	public void save(TripsVo tripsVo) {	
		Integer id = tripsVo.getId();
		Boolean objectAlreadyExists=tripsDao.existsById(id);
		if(!objectAlreadyExists) {
			Trips trips = new Trips();
			BeanUtils.copyProperties(tripsVo, trips);
			tripsDao.save(trips);
		}else {
			throw new EntityExistsException();
		}
		
	}
	
	@Override
	@Transactional
	public void update(TripsVo tripsVo) {
		Integer id = tripsVo.getId();
		Boolean objectExists=tripsDao.existsById(id);
		if(objectExists) {
			Trips trips = new Trips();
			BeanUtils.copyProperties(tripsVo, trips);
			tripsDao.save(trips);
		}else {
			throw new EntityNotFoundException();
		}
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		Boolean objectExists=tripsDao.existsById(id);
		if(objectExists) {
			tripsDao.deleteById(id);
		}else {
			throw new EntityNotFoundException();
		}
	}

	@Override
	@Transactional
	public TripsVo get(Integer id) {
		Optional<Trips> tripsOptional = tripsDao.findById(id);
		TripsVo tripsVo=null;
		if(tripsOptional.isPresent()) {
			tripsVo = new TripsVo();
			BeanUtils.copyProperties(tripsOptional.get(), tripsVo);	
		}else {
			throw new EntityNotFoundException();
		}
		
		return tripsVo;
	}

	@Override
	@Transactional
	public List<TripsVo> getAll() {
		List<Trips> tripsList = tripsDao.findAll();
		List<TripsVo> tripsVoList = new ArrayList<>();
		if (tripsList != null && !tripsList.isEmpty()) {
			for (Trips trips : tripsList) {
				TripsVo tripsVo = new TripsVo();
				BeanUtils.copyProperties(trips, tripsVo);
				tripsVoList.add(tripsVo);
			}
		}
		return tripsVoList;
	}

}

