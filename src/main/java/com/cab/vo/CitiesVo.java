package com.cab.vo;

public class CitiesVo {

	private Integer id ;

	private String name ;

	private String state ;

	public CitiesVo(){
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