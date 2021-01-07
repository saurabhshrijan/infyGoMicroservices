package com.infosys.infyGo.BookFlight.dto;

import java.util.List;

import com.infosys.infyGo.BookFlight.entity.PassengerEntity;

public class BookingDTO {
	private int pnr;
	private double totalFare;

	private List<PassengerEntity> passengerList;

	public int getPnr() {
		return pnr;
	}

	public void setPnr(int pnr) {
		this.pnr = pnr;
	}

	public double getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(double totalFare) {
		this.totalFare = totalFare;
	}

	public List<PassengerEntity> getPassengerList() {
		return passengerList;
	}

	public void setPassengerList(List<PassengerEntity> passengerList) {
		this.passengerList = passengerList;
	}
}
