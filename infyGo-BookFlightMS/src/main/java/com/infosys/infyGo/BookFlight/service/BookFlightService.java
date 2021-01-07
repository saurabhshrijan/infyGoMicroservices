package com.infosys.infyGo.BookFlight.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.infyGo.BookFlight.dto.BookingDTO;
import com.infosys.infyGo.BookFlight.dto.FlightDTO;
import com.infosys.infyGo.BookFlight.dto.PassengerDTO;
import com.infosys.infyGo.BookFlight.entity.PassengerEntity;
import com.infosys.infyGo.BookFlight.entity.TicketEntity;
import com.infosys.infyGo.BookFlight.repository.PassengerRepository;
import com.infosys.infyGo.BookFlight.repository.TicketRepository;

@Service
public class BookFlightService {
	
	@Autowired
	TicketRepository ticketRepository;
	@Autowired
	PassengerRepository passengerRepository;

	public BookingDTO bookTicket(@Valid PassengerDTO passengerDetails, FlightDTO flightDTO, TicketEntity ticket) {

		ticketRepository.save(ticket);
		List<PassengerEntity> passengersList = new ArrayList<PassengerEntity>();
		
		for(PassengerEntity passenger: passengerDetails.getPassengerList()) {
			passenger.setTicket(ticket);
			passengersList.add(passenger);
		}
		passengerRepository.saveAll(passengersList);
		
		BookingDTO finalBookigDetails = new BookingDTO();
		finalBookigDetails.setPnr(ticket.getPnr());
		finalBookigDetails.setPassengerList(passengersList);
		finalBookigDetails.setTotalFare(ticket.getTotalFare());
		
		return finalBookigDetails;
	}

}
