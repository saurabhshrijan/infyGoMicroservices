package com.infosys.infyGo.customer.service;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.infyGo.customer.dto.LoginDTO;
import com.infosys.infyGo.customer.entity.Customer;
import com.infosys.infyGo.customer.repository.CustomerRepository;

@Service
public class LoginService {

	Logger logger= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CustomerRepository customerRepository;

	public Boolean isUserValid(@Valid LoginDTO login) {
		
		logger.info("hitting service with login request with customer {}",login.toString());
		
		Customer customer = customerRepository.findById(login.getUserId()).get();
		
		logger.info("getting customer details from db after login details",customer);
		
		if (customer == null) {
			return false;
		} else if (!(customer.getPassword().equals(login.getPassword()))) {
			return false;
		}
		return true;
	}

}
