package com.outdoorsy.challenge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import com.outdoorsy.challenge.domain.Rental;
import com.outdoorsy.challenge.dto.PriceSearchRequestParameters;
import com.outdoorsy.challenge.dto.SearchRequestParameters;
import com.outdoorsy.challenge.exception.RentalNotFoundException;
import com.outdoorsy.challenge.repository.RentalRepository;

@ExtendWith(MockitoExtension.class)
public class RentalServiceUnitTest {

  private static final Integer EXISTING_RENTAL_ID = 1;
  private static final Integer NON_EXISTING_RENTAL_ID = 1;

  private Integer nearDistance = 100;

  @Mock
  private RentalRepository rentalRepository;

  @Mock
  private Pageable pageable;

  @InjectMocks
  private RentalService rentalService;

  @BeforeEach
  public void setup() {
    ReflectionTestUtils.setField(rentalService, "nearDistance", nearDistance);
  }

  @Test
  public void testSearchByPrice() {
    Long minPrice = 9000L;
    Long maxPrice = 75000L;
    SearchRequestParameters searchRequestParameters = new SearchRequestParameters();
    PriceSearchRequestParameters priceSearchRequestParameters = new PriceSearchRequestParameters();
    priceSearchRequestParameters.setMin(minPrice);
    priceSearchRequestParameters.setMax(maxPrice);
    searchRequestParameters.setPrice(priceSearchRequestParameters);

    rentalService.search(searchRequestParameters, pageable);

    verify(rentalRepository).findAllByPricePerDayIsBetween(eq(minPrice), eq(maxPrice), eq(pageable));
  }

  @Test
  public void testSearchByIds() {
    List<Integer> ids = mock(List.class);
    SearchRequestParameters searchRequestParameters = new SearchRequestParameters();
    searchRequestParameters.setIds(ids);

    rentalService.search(searchRequestParameters, pageable);

    verify(rentalRepository).findAllByIdIn(eq(ids), eq(pageable));
  }

  @Test
  public void testSearchByNear() {
    double lat = 33.64;
    double lng = -117.93;
    String nearRequestParam = lat + "," + lng;
    SearchRequestParameters searchRequestParameters = new SearchRequestParameters();
    searchRequestParameters.setNear(nearRequestParam);

    rentalService.search(searchRequestParameters, pageable);

    verify(rentalRepository).findNearRentals(eq(nearDistance), eq(lat), eq(lng), eq(pageable));
  }

  @Test
  public void testSearchWithoutRequestParams() {
    SearchRequestParameters searchRequestParameters = new SearchRequestParameters();
    rentalService.search(searchRequestParameters, pageable);

    verify(rentalRepository).findAll(eq(pageable));
  }

  @Test
  public void testFindByExistingId() throws RentalNotFoundException {
    Rental expected = mock(Rental.class);
    when(rentalRepository.findById(EXISTING_RENTAL_ID)).thenReturn(Optional.of(expected));

    Rental actual = rentalService.findById(EXISTING_RENTAL_ID);

    assertEquals(expected, actual);
  }

  @Test
  public void testFindByNonExistingId() {
    when(rentalRepository.findById(NON_EXISTING_RENTAL_ID)).thenReturn(Optional.empty());

    Assertions.assertThrows(RentalNotFoundException.class, () -> rentalService.findById(NON_EXISTING_RENTAL_ID));
  }
}
