package com.infosys.infyGo.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.infyGo.payment.dto.CreditCardDetails;
import com.infosys.infyGo.payment.repository.CreditCardRepository;

@Service
public class CreditCardService {

	@Autowired
	CreditCardRepository creditCardRepository;
	
	public CreditCardDetails findById(String cardNumber) {
		return creditCardRepository.findById(cardNumber).get().toDTO();
	}
}
