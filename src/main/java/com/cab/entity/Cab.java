package com.cab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cab")
public class Cab {

	@Id
	@Column(name="id")
	private Integer id ;

	@Column(name="driverName")
	private String driverName ;

	@Column(name="capacity")
	private Integer capacity ;

	@Column(name="type")
	private String type ;

	@Column(name="status")
	private Integer status ;

	@Column(name="enable")
	private Integer enable ;

	@Column(name="citiyId")
	private Integer citiyId ;

	public Cab(){
		super();
	}

	public Integer getId(){
		return this.id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getDriverName(){
		return this.driverName;
	}

	public void setDriverName(String driverName){
		this.driverName = driverName;
	}

	public Integer getCapacity(){
		return this.capacity;
	}

	public void setCapacity(Integer capacity){
		this.capacity = capacity;
	}

	public String getType(){
		return this.type;
	}

	public void setType(String type){
		this.type = type;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getEnable(){
		return this.enable;
	}

	public void setEnable(Integer enable){
		this.enable = enable;
	}

	public Integer getCitiyId(){
		return this.citiyId;
	}

	public void setCitiyId(Integer citiyId){
		this.citiyId = citiyId;
	}

}