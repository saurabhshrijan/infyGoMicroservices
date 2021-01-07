package com.infosys.infyGo.BookFlight.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.validation.Errors;

import com.infosys.infyGo.BookFlight.dto.BookingDTO;
import com.infosys.infyGo.BookFlight.dto.FlightDTO;
import com.infosys.infyGo.BookFlight.dto.PassengerDTO;
import com.infosys.infyGo.BookFlight.entity.PassengerEntity;
import com.infosys.infyGo.BookFlight.entity.TicketEntity;
import com.infosys.infyGo.BookFlight.service.BookFlightService;

@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookFlightController {
	
	Logger logger  = LoggerFactory.getLogger(this.getClass());

	@Autowired
	BookFlightService bookflightservice;
	
	@Value("${payment.uri}")
	String paymentURI;
	
	@Value("${flight.uri}")
	String flightURI;
	
	private TicketEntity ticket;
	
	public BookFlightController() {
		ticket = new TicketEntity();		
	}
	
	@PostMapping(value = "/{flightId}/{userId}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<BookingDTO> bookFlight(@PathVariable("flightId") String flightId,
		 @Valid @RequestBody PassengerDTO passengerDetails, @PathVariable("userId") String userId,Errors errors){
			
		    if (errors.hasErrors()) {
		    	return null;
		    }
		if(passengerDetails.getPassengerList().isEmpty()) {
			return null;
		}
		
		FlightDTO flightDTO = fetchFlightDetails(flightId);
		if(flightDTO.getFlightId() ==flightId && passengerDetails.getPassengerList().size() < Integer.valueOf(flightDTO.getSeatCount())) {
			
			
			ResponseEntity<Boolean> paymentStatus = new RestTemplate().getForObject(paymentURI+"/payment", null);
			
        	if(paymentStatus.getStatusCode().value()== 200) {
        		
        		int pnr = (int) (Math.random() * 1858955);
        		ticket.setPnr(pnr);
        		double fare = Double.valueOf(flightDTO.getFare());
        		System.out.println("Fare per person:****** " + fare);
        		System.out.println("List size:****** " + passengerDetails.getPassengerList().size());
        		
        		double totalFare = fare * (passengerDetails.getPassengerList().size());
        		ticket.setDepartureDate(flightDTO.getJourneyDate());
        		ticket.setDepartureTime(flightDTO.getDepartureTime());
        		ticket.setFlightId(flightDTO.getFlightId());
        		ticket.setUserId(userId);		
        		ticket.setTotalFare(totalFare);
        		ticket.setNoOfSeats(passengerDetails.getPassengerList().size());
        		
        		//make a rest call to update no of seat left in that flight
        		
        		return new ResponseEntity<BookingDTO>(bookflightservice.bookTicket(passengerDetails,flightDTO,ticket), 
        				HttpStatus.OK);
        		
        	}
        	
		}
        
		return null;
		

	}
	
	public FlightDTO fetchFlightDetails(String flightId) {
		
		FlightDTO searchedFlight = new RestTemplate().getForObject(flightURI+"/flightId", FlightDTO.class);
		return searchedFlight;
	}

	
}
