package com.outdoorsy.challenge.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.outdoorsy.challenge.domain.Rental;
import com.outdoorsy.challenge.dto.SearchRequestParameters;
import com.outdoorsy.challenge.service.RentalService;

@RestController
@RequestMapping(value = "/campervans", produces = APPLICATION_JSON_VALUE)
public class RentalController {

  @Autowired
  private RentalService rentalService;

  @GetMapping
  @ResponseBody
  public Page<Rental> search(SearchRequestParameters searchRequestParameters, Pageable pageable) {
    return rentalService.search(searchRequestParameters, pageable);
  }
}
