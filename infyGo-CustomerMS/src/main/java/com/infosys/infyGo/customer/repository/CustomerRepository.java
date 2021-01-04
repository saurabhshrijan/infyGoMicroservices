package com.infosys.infyGo.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.infyGo.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}