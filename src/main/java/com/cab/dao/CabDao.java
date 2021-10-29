package com.cab.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cab.entity.Cab;

@Repository
public interface CabDao extends JpaRepository<Cab, Integer>{
	


}