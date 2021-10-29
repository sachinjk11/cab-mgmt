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

import com.cab.dao.CitiesDao;
import com.cab.entity.Cities;
import com.cab.service.CitiesService;
import com.cab.vo.CitiesVo;

@Service
public class CitiesServiceImpl implements CitiesService {

	@Autowired
	CitiesDao citiesDao;

	@Override
	@Transactional
	public void save(CitiesVo citiesVo) {	
		Integer id = citiesVo.getId();
		Boolean objectAlreadyExists=citiesDao.existsById(id);
		if(!objectAlreadyExists) {
			Cities cities = new Cities();
			BeanUtils.copyProperties(citiesVo, cities);
			citiesDao.save(cities);
		}else {
			throw new EntityExistsException();
		}
		
	}
	
	@Override
	@Transactional
	public void update(CitiesVo citiesVo) {
		Integer id = citiesVo.getId();
		Boolean objectExists=citiesDao.existsById(id);
		if(objectExists) {
			Cities cities = new Cities();
			BeanUtils.copyProperties(citiesVo, cities);
			citiesDao.save(cities);
		}else {
			throw new EntityNotFoundException();
		}
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		Boolean objectExists=citiesDao.existsById(id);
		if(objectExists) {
			citiesDao.deleteById(id);
		}else {
			throw new EntityNotFoundException();
		}
	}

	@Override
	@Transactional
	public CitiesVo get(Integer id) {
		Optional<Cities> citiesOptional = citiesDao.findById(id);
		CitiesVo citiesVo=null;
		if(citiesOptional.isPresent()) {
			citiesVo = new CitiesVo();
			BeanUtils.copyProperties(citiesOptional.get(), citiesVo);	
		}else {
			throw new EntityNotFoundException();
		}
		
		return citiesVo;
	}

	@Override
	@Transactional
	public List<CitiesVo> getAll() {
		List<Cities> citiesList = citiesDao.findAll();
		List<CitiesVo> citiesVoList = new ArrayList<>();
		if (citiesList != null && !citiesList.isEmpty()) {
			for (Cities cities : citiesList) {
				CitiesVo citiesVo = new CitiesVo();
				BeanUtils.copyProperties(cities, citiesVo);
				citiesVoList.add(citiesVo);
			}
		}
		return citiesVoList;
	}

}

