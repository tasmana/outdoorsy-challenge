package com.outdoorsy.challenge.aggregator;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.outdoorsy.challenge.dto.RentalResponseDTO;
import com.outdoorsy.challenge.dto.SearchRequestParameters;
import com.outdoorsy.challenge.exception.RentalNotFoundException;
import com.outdoorsy.challenge.service.RentalService;

@Component
public class RentalAggregator {

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private RentalService rentalService;

  public Page<RentalResponseDTO> search(SearchRequestParameters searchParams, Pageable pageable) {
    return rentalService
        .search(searchParams, pageable)
        .map(rental -> modelMapper.map(rental, RentalResponseDTO.class));
  }

  public RentalResponseDTO findById(Integer id) throws RentalNotFoundException {
    return modelMapper.map(rentalService.findById(id), RentalResponseDTO.class);
  }
}
