package com.infosys.infyGo.customer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.infyGo.customer.dto.CustomerDTO;
import com.infosys.infyGo.customer.entity.Customer;
import com.infosys.infyGo.customer.repository.CustomerRepository;

@Service
public class RegistrationService {
	
	Logger logger=LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomerRepository customerRepository;

	public Boolean insertUser(CustomerDTO customer) {
		
		logger.info("hitting service with inserting request with",customer.toString());
		
		Customer cust = customerRepository.saveAndFlush(customer.createEntity());
		if (cust == null) {
			return false;
		} else {
			return true;
		}
	}

}
