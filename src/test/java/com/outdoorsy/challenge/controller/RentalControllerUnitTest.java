package com.outdoorsy.challenge.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import com.outdoorsy.challenge.aggregator.RentalAggregator;
import com.outdoorsy.challenge.dto.SearchRequestParameters;
import com.outdoorsy.challenge.exception.RentalNotFoundException;

@ExtendWith(MockitoExtension.class)
public class RentalControllerUnitTest {

  private static final Integer RENTAL_ID = 1;

  @Mock
  private RentalAggregator rentalAggregator;

  @InjectMocks
  private RentalController rentalController;

  @Test
  public void testSearch() {
    SearchRequestParameters searchRequestParameters = mock(SearchRequestParameters.class);
    Pageable pageable = mock(Pageable.class);

    rentalAggregator.search(searchRequestParameters, pageable);

    verify(rentalAggregator).search(searchRequestParameters, pageable);
  }

  @Test
  public void testFindById() throws RentalNotFoundException {
    rentalController.findById(RENTAL_ID);

    verify(rentalAggregator).findById(RENTAL_ID);
  }
}
