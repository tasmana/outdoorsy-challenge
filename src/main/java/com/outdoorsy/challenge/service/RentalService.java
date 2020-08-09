package com.outdoorsy.challenge.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.outdoorsy.challenge.domain.Rental;
import com.outdoorsy.challenge.dto.SearchRequestParameters;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service
public class RentalService {

  public Page<Rental> search(SearchRequestParameters searchRequestParameters, Pageable pageable) {
    throw new NotImplementedException();
  }
}
