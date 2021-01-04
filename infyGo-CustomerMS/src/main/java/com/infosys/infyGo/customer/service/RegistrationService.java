package com.infosys.infyGo.customer.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.infosys.infyGo.customer.dto.CustomerDTO;
import com.infosys.infyGo.customer.entity.Customer;
import com.infosys.infyGo.customer.repository.CustomerRepository;

public class RegistrationService {

	@Autowired
	private CustomerRepository customerRepository;

	public Boolean insertUser(CustomerDTO customer) {
		Customer cust = customerRepository.saveAndFlush(customer.createEntity());
		if (cust == null) {
			return false;
		} else {
			return true;
		}
	}

}
