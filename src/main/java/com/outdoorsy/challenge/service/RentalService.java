package com.outdoorsy.challenge.service;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.outdoorsy.challenge.domain.Rental;
import com.outdoorsy.challenge.dto.SearchRequestParameters;
import com.outdoorsy.challenge.exception.RentalNotFoundException;
import com.outdoorsy.challenge.repository.RentalRepository;

@Service
public class RentalService {

  @Value("${rental.near.distance:100}")
  private Integer nearDistance;

  @Autowired
  private RentalRepository rentalRepository;

  public Rental findById(Integer id) throws RentalNotFoundException {
    return rentalRepository.findById(id).orElseThrow(() -> new RentalNotFoundException(id));
  }

  public Page<Rental> search(SearchRequestParameters searchRequestParameters, Pageable pageable) {
    if (searchRequestParameters.getPrice() != null) {
      return rentalRepository
          .findAllByPricePerDayIsBetween(searchRequestParameters.getPrice().getMin(), searchRequestParameters.getPrice().getMax(), pageable);
    } else if (!CollectionUtils.isEmpty(searchRequestParameters.getIds())) {
      return rentalRepository.findAllByIdIn(searchRequestParameters.getIds(), pageable);
    } else if (isNotEmpty(searchRequestParameters.getNear())) {
      String[] coordinates = searchRequestParameters.getNear().split(",");
      return rentalRepository.findNearRentals(nearDistance, Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]), pageable);
    }

    return rentalRepository.findAll(pageable);
  }
}
