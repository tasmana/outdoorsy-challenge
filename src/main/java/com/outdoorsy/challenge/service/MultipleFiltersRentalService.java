package com.outdoorsy.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.outdoorsy.challenge.domain.Rental;
import com.outdoorsy.challenge.dto.SearchRequestParameters;
import com.outdoorsy.challenge.exception.RentalNotFoundException;
import com.outdoorsy.challenge.repository.NativeSqlRentalRepository;
import com.outdoorsy.challenge.repository.RentalFilters;

@Component
@Primary
@ConditionalOnProperty(value = "multipleFilters.enabled", havingValue = "true")
public class MultipleFiltersRentalService implements RentalService {

  @Autowired
  private NativeSqlRentalRepository nativeSqlRentalRepository;

  @Autowired
  @Qualifier("singleFilterRentalService")
  private RentalService singleFilterRentalService;

  @Override
  public Page<Rental> search(SearchRequestParameters searchRequestParameters, Pageable pageable) {
    //TODO create a mapper for SearchRequestParameters -> RentalFilters
    RentalFilters rentalFilters = new RentalFilters();
    return nativeSqlRentalRepository.findByFilters(rentalFilters, pageable);
  }

  @Override
  public Rental findById(Integer id) throws RentalNotFoundException {
    // proxying to SingleFilterRentalService as the implementation here is the same;
    return singleFilterRentalService.findById(id);
  }
}
