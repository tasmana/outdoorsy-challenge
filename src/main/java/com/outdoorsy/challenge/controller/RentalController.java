package com.outdoorsy.challenge.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.outdoorsy.challenge.aggregator.RentalAggregator;
import com.outdoorsy.challenge.dto.RentalResponseDTO;
import com.outdoorsy.challenge.dto.SearchRequestParameters;
import com.outdoorsy.challenge.exception.RentalNotFoundException;

@RestController
@RequestMapping(value = "/campervans", produces = APPLICATION_JSON_VALUE)
public class RentalController {

  @Autowired
  private RentalAggregator rentalAggregator;

  @GetMapping
  @ResponseBody
  public Page<RentalResponseDTO> search(@Valid SearchRequestParameters searchRequestParameters, Pageable pageable) {
    return rentalAggregator.search(searchRequestParameters, pageable);
  }

  @GetMapping(path = "/{id}")
  @ResponseBody
  public RentalResponseDTO findById(@PathVariable Integer id) throws RentalNotFoundException {
    return rentalAggregator.findById(id);
  }
}
