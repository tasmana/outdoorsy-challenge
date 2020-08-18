package com.outdoorsy.challenge.aggregator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.google.common.collect.Lists;
import com.outdoorsy.challenge.domain.Rental;
import com.outdoorsy.challenge.dto.RentalResponseDTO;
import com.outdoorsy.challenge.dto.SearchRequestParameters;
import com.outdoorsy.challenge.exception.RentalNotFoundException;
import com.outdoorsy.challenge.service.SingleFilterRentalService;

@ExtendWith(MockitoExtension.class)
public class RentalAggregatorUnitTest {

  private static final Integer RENTAL_ID = 1;

  @Mock
  private ModelMapper modelMapper;

  @Mock
  private SingleFilterRentalService rentalService;

  @InjectMocks
  private RentalAggregator rentalAggregator;

  @Test
  public void testSearch() {
    SearchRequestParameters searchRequestParameters = mock(SearchRequestParameters.class);
    Pageable pageable = mock(Pageable.class);
    Rental returnedRental = mock(Rental.class);
    Page<Rental> searchResult = new PageImpl<>(Lists.newArrayList(returnedRental));
    when(rentalService.search(searchRequestParameters, pageable)).thenReturn(searchResult);

    rentalAggregator.search(searchRequestParameters, pageable);

    verify(modelMapper).map(returnedRental, RentalResponseDTO.class);
  }

  @Test
  public void testFindById() throws RentalNotFoundException {
    Rental returnedRental = mock(Rental.class);
    when(rentalService.findById(RENTAL_ID)).thenReturn(returnedRental);

    rentalAggregator.findById(RENTAL_ID);

    verify(modelMapper).map(returnedRental, RentalResponseDTO.class);
  }
}
