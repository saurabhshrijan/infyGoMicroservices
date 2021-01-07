package com.infosys.infyGo.SearchFlight.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.infyGo.SearchFlight.controller.entity.SearchFlightEntity;
import com.infosys.infyGo.SearchFlight.dto.SearchFlightDTO;
import com.infosys.infyGo.SearchFlight.repository.FlightsRepository;

@Service
public class SearchFlightService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	FlightsRepository flightsRepository;
	
	public List<String> getSources(){
		List<String> sources = flightsRepository.findFlightSources();
		if (sources == null) {
			logger.info("No details available");
			return null;
		} else {
			return sources;
		}
	}

	public List<String> getDestinationss(){
		List<String> destinations = flightsRepository.findFlightDestinations();
		if (destinations == null) {
			logger.info("No details available");
			return null;
		} else {
			return destinations;
		}
	}

	public List<SearchFlightDTO> getFlights(String source, String destination, Date journeyDate) {

		List<SearchFlightEntity> flights = flightsRepository.findFlightDetails(source, destination, journeyDate);

		List<SearchFlightDTO> availableFlights = new ArrayList<SearchFlightDTO>();
		for (SearchFlightEntity f : flights) {
			SearchFlightDTO flight = new SearchFlightDTO();
			flight.setFlightId(f.getFlightId());
			flight.setSource(f.getSource());
			flight.setDestination(f.getDestination());
			flight.setJourneyDate(f.getFlightAvailableDate());
			flight.setDepartureTime(f.getDepartureTime());
			flight.setArrivalTime(f.getArrivalTime());
			flight.setSeatCount(f.getSeatCount().toString());
			flight.setAirlines(f.getAirlines());
			flight.setFare(Double.toString(f.getFare()));
			availableFlights.add(flight);
		}

		return availableFlights;

	}

	public void updateFlight(String flightId, int noOfSeats){
		SearchFlightEntity flight = flightsRepository.findById(flightId).get();

		if (flight == null) {
			logger.info("No flight for the given details");
		} else {

			int count = flight.getSeatCount() - Integer.valueOf(noOfSeats);
			flight.setSeatCount(count);
			flightsRepository.saveAndFlush(flight);

		}

	}

	public SearchFlightEntity getFlights(String flightId) {
		return flightsRepository.findById("F101").get();

	}

	
}
