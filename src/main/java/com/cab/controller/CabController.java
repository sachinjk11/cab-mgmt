package com.cab.controller;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cab.exception.NoOngoingTripException;
import com.cab.exception.ServiceException;
import com.cab.service.CabService;
import com.cab.vo.CabVo;
import com.cab.vo.TripsVo;

@RestController
public class CabController {

	@Autowired
	CabService cabService;

	@PostMapping("cab")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void save(@RequestBody CabVo cabVo) {
		cabService.save(cabVo);
	}

	@GetMapping("cab/{id}")
	public CabVo get( @PathVariable Integer id) {
		return cabService.get(id);
	}
	
	@GetMapping("ride/book/{id}")
	public @ResponseBody ResponseEntity<TripsVo> bookRide( @PathVariable Integer id) throws ServiceException {
		TripsVo trip = cabService.bookRide(id);
		return new ResponseEntity<TripsVo>(trip, new HttpHeaders(),HttpStatus.OK);
	}
	@GetMapping("ride/complete/{id}")
	public @ResponseBody ResponseEntity<TripsVo> completeRide( @PathVariable Integer id)  throws NoOngoingTripException  {
		TripsVo trip = cabService.CompleteRide(id);
		return new ResponseEntity<TripsVo>(trip, new HttpHeaders(),HttpStatus.OK);
	}

	@GetMapping("cab")
	public List<CabVo>  getAll() {
		return cabService.getAll();
	}
	
	@PutMapping("cab")
	public void update(@RequestBody CabVo cabVo) {
		cabService.update(cabVo);
	}
	
	@DeleteMapping("cab/{id}")
	public void delete( @PathVariable Integer id) {
		cabService.delete(id);
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
