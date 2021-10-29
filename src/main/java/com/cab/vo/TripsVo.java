package com.cab.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TripsVo {

	private Integer id ;

	private Integer cabId ;

	private Integer cityId ;

	private Date startTime ;

	private Date endTime ;

	private Integer status ;

	public TripsVo(){
		super();
	}
}