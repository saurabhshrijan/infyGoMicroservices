package com.infosys.infyGo.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.infyGo.customer.dto.CustomerDTO;
import com.infosys.infyGo.customer.dto.LoginDTO;
import com.infosys.infyGo.customer.service.LoginService;
import com.infosys.infyGo.customer.service.RegistrationService;



@CrossOrigin
@RestController
public class CustomerController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LoginService loginService;

	@Autowired
	RegistrationService registrationService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Boolean> Validate(@Valid @RequestBody LoginDTO login){
		
		logger.info("login request with customer {}",login.toString());
		
		Boolean result = false;
		result = loginService.isUserValid(login);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Boolean> customer(@RequestBody CustomerDTO customer){
		
		logger.info("registration request with customer{}",customer.toString());
		
		Boolean result = registrationService.insertUser(customer);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

}
