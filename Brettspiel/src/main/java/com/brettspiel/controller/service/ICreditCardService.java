package com.brettspiel.controller.service;

import java.util.Optional;

import com.brettspiel.model.CreditCard;

public interface ICreditCardService extends ICrudService<CreditCard> {
	public Optional<CreditCard> findByNumber(Integer number);
}
