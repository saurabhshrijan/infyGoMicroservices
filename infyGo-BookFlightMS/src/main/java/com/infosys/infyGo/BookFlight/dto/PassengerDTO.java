package com.infosys.infyGo.BookFlight.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.infosys.infyGo.BookFlight.entity.PassengerEntity;

public class PassengerDTO {
	@NotEmpty(message = "Passenger List cannot be empty for booking!")
	List<PassengerEntity> passengerList;

	public List<PassengerEntity> getPassengerList() {
		return passengerList;
	}

	public PassengerDTO() {
		super();
	}

	public PassengerDTO(List<PassengerEntity> passengerList) {
		super();
		this.passengerList = passengerList;
	}

	public void setPassengerList(List<PassengerEntity> passengerList) {
		this.passengerList = passengerList;
	}

	@Override
	public String toString() {
		return "PassengerDetails [passengerList=" + passengerList + "]";
	}
	
	
}
