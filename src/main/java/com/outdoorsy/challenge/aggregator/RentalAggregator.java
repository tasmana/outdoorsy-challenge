package com.outdoorsy.challenge.aggregator;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.outdoorsy.challenge.dto.RentalResponseDTO;
import com.outdoorsy.challenge.dto.SearchRequestParameters;
import com.outdoorsy.challenge.service.RentalService;

@Component
public class RentalAggregator {

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private RentalService rentalService;

  public Page<RentalResponseDTO> search(SearchRequestParameters searchParams, Pageable pageable) {
    return modelMapper.map(rentalService.search(searchParams, pageable), new TypeToken<Page<RentalResponseDTO>>() {}.getType());
  }
}
