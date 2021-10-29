package com.cab.controller;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cab.service.CitiesService;
import com.cab.vo.CitiesVo;

@RestController
public class CitiesController {

	@Autowired
	CitiesService citiesService;

	@PostMapping("cities")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void save(CitiesVo citiesVo) {
		citiesService.save(citiesVo);
	}

	@GetMapping("cities/{id}")
	public CitiesVo get( @PathVariable Integer id) {
		return citiesService.get(id);
	}

	@GetMapping("cities")
	public List<CitiesVo>  getAll() {
		return citiesService.getAll();
	}
	
	@PutMapping("cities")
	public void update(CitiesVo citiesVo) {
		citiesService.update(citiesVo);
	}
	
	@DeleteMapping("cities/{id}")
	public void delete( @PathVariable Integer id) {
		citiesService.delete(id);
	}
	
	@ExceptionHandler(EntityExistsException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public String handleEntityExistsException(EntityExistsException e) {
	    return e.getMessage();
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleEntityNotFoundException(EntityNotFoundException e) {
	    return e.getMessage();
	}
}
