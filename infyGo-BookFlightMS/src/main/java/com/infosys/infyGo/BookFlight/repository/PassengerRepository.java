package com.infosys.infyGo.BookFlight.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.infyGo.BookFlight.entity.PassengerEntity;

public interface PassengerRepository extends JpaRepository<PassengerEntity, Integer> {

}
