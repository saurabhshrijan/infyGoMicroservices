package com.infosys.infyGo.payment.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.infyGo.payment.dto.CreditCardDetails;
import com.infosys.infyGo.payment.service.CreditCardService;

@RestController
@CrossOrigin
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	private CreditCardService creditCardService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Boolean> validateCreditCard(@RequestBody CreditCardDetails creditCard) {
		CreditCardDetails cardDetails;
		boolean result = false;
		try {

			cardDetails = (CreditCardDetails) creditCardService.findById(creditCard.getCardNumber());
	
			
			if (cardDetails != null) {
				result = creditCard.getApin().equals(cardDetails.getApin())
						&& creditCard.getCvv().equals(cardDetails.getCvv())
						&& creditCard.getCardHolderName().equals(cardDetails.getCardHolderName());
				
			}
			
		} catch (Exception a) {
			
			return new ResponseEntity<Boolean>(result,HttpStatus.OK);
		}

		return new ResponseEntity<Boolean>(result, HttpStatus.OK);

	}
}
