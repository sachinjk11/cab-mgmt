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

import com.cab.service.TripsService;
import com.cab.vo.TripsVo;

@RestController
public class TripsController {

	@Autowired
	TripsService tripsService;

	@PostMapping("trips")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void save(TripsVo tripsVo) {
		tripsService.save(tripsVo);
	}

	@GetMapping("trips/{id}")
	public TripsVo get( @PathVariable Integer id) {
		return tripsService.get(id);
	}

	@GetMapping("trips")
	public List<TripsVo>  getAll() {
		return tripsService.getAll();
	}
	
	@PutMapping("trips")
	public void update(TripsVo tripsVo) {
		tripsService.update(tripsVo);
	}
	
	@DeleteMapping("trips/{id}")
	public void delete( @PathVariable Integer id) {
		tripsService.delete(id);
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
