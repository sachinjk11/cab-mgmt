package com.cab.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="history")
public class Trips {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id ;

	@Column(name="cabId")
	private Integer cabId ;

	@Column(name="cityId")
	private Integer cityId ;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="startTime")
	private Date startTime ;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="endTime")
	private Date endTime ;

	@Column(name="status")
	private Integer status ;

	public Trips(){
		super();
	}
}