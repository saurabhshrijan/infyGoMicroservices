package com.infosys.infyGo.SearchFlight.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infosys.infyGo.SearchFlight.controller.entity.SearchFlightEntity;

@Repository
public interface FlightsRepository extends JpaRepository<SearchFlightEntity, String>{
	@Query("select f from SearchFlightEntity f where f.source=:source and f.destination=:dest and f.flightAvailableDate=:jdate")
	List<SearchFlightEntity> findFlightDetails(@Param("source") String source, @Param("dest") String destination,
			@Param("jdate") Date date);

	@Query("select f from SearchFlightEntity f where f.flightId=:flightId")
	SearchFlightEntity findFlight(@Param("flightId") String flightId);

	@Query("select f.source from SearchFlightEntity f")
	List<String> findFlightSources();

	@Query("select f.destination from SearchFlightEntity f")
	List<String> findFlightDestinations();

}
