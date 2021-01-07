package com.infosys.infyGo.SearchFlight.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.infyGo.SearchFlight.controller.entity.SearchFlightEntity;
import com.infosys.infyGo.SearchFlight.dto.SearchFlightDTO;
import com.infosys.infyGo.SearchFlight.service.SearchFlightService;

@RestController
@CrossOrigin
@RequestMapping("/flights")
public class SearchFlightController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SearchFlightService flightService;
	
	@GetMapping("/{flightId}")
	public SearchFlightEntity getFlights(@PathVariable("flightId") String flightId){
		System.out.println("flight id" + flightId);
		return flightService.getFlights(flightId);
	}

	@RequestMapping(value = "/sources", method = RequestMethod.GET)
	public List<String> getFlightSources(){
		logger.info("In get sources");
		return flightService.getSources();
	}

	@RequestMapping(value = "/destinations", method = RequestMethod.GET)
	public List<String> getFlightDestinations(){
		logger.info("In get sources");
		return flightService.getDestinationss();
	}

	@RequestMapping(value = "/{source}/{destination}/{journeyDate}", method = RequestMethod.GET)
	public ResponseEntity<List<SearchFlightDTO>> getFlightDetails(@PathVariable String source,
			HttpServletResponse response, @PathVariable String destination, @PathVariable Date journeyDate) {
		List<SearchFlightDTO> availableFlights = flightService.getFlights(source, destination, journeyDate);
		return new ResponseEntity<List<SearchFlightDTO>>(availableFlights, HttpStatus.OK);

	}

	@RequestMapping(value = "/{flightId}/{noOfSeats}")
	public void updateFlightSeat(@PathVariable String flightId, @PathVariable int noOfSeats){
		flightService.updateFlight(flightId, noOfSeats);

	}
	@RequestMapping("/book")
	public void bookFlight() {
		
	}
}
