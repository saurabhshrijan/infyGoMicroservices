package com.infosys.infyGo.customer.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import com.infosys.infyGo.customer.dto.LoginDTO;
import com.infosys.infyGo.customer.entity.Customer;
import com.infosys.infyGo.customer.repository.CustomerRepository;

public class LoginService {

	@Autowired
	private CustomerRepository customerRepository;

	public Boolean isUserValid(@Valid LoginDTO login) {
		Customer customer = customerRepository.findById(login.getUserId()).get();
		if (customer == null) {
			return false;
		} else if (!(customer.getPassword().equals(login.getPassword()))) {
			return false;
		}
		return true;
	}

}
