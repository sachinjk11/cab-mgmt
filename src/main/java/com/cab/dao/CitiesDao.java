package com.cab.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cab.entity.Cities;

@Repository
public interface CitiesDao extends JpaRepository<Cities, Integer>{

}