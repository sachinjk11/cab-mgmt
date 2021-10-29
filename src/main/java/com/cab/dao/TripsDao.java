package com.cab.dao;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cab.entity.Trips;

@Repository
public interface TripsDao extends JpaRepository<Trips, Integer>{
	
	@Query(value="select cab_id as cabId, sum(case when end_time is null then TIMESTAMPDIFF(SECOND,start_time, now()) else TIMESTAMPDIFF(SECOND,start_time,end_time) end) as waitTime from history where status=0 group by 1 order by 2 desc", nativeQuery = true)
	public LinkedList<Mapping> getMappingData();
	
	interface Mapping {
		Integer getCabId();
		Integer getWaitTime();
	}
	
	@Query(value = "select * from history where status=1  and end_time is null and  cab_id  = :cabId ", nativeQuery = true)
    public Trips getOngoingRide(@Param("cabId") Integer cabId);
	
	@Query(value = "select * from history where status=0  and end_time is null and  cab_id  = :cabId ", nativeQuery = true)
    public Trips getWaitRide(@Param("cabId") Integer cabId);

}