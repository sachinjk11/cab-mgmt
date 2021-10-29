package com.cab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cities")
public class Cities {

	@Id
	@Column(name="id")
	private Integer id ;

	@Column(name="name")
	private String name ;

	@Column(name="state")
	private String state ;

	public Cities(){
		super();
	}

	public Integer getId(){
		return this.id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getState(){
		return this.state;
	}

	public void setState(String state){
		this.state = state;
	}

}