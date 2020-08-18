package com.outdoorsy.challenge.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.outdoorsy.challenge.domain.Rental;
import com.outdoorsy.challenge.dto.SearchRequestParameters;
import com.outdoorsy.challenge.exception.RentalNotFoundException;

public interface RentalService {

  Page<Rental> search(SearchRequestParameters searchRequestParameters, Pageable pageable);

  Rental findById(Integer id) throws RentalNotFoundException;

}
