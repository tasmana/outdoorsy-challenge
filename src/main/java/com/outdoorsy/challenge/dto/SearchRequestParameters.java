package com.outdoorsy.challenge.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import com.outdoorsy.challenge.validation.OneFilterAllowed;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@OneFilterAllowed(message = "You can pass only one filter per a request. Allowed filters: [near, ids, price]")
public class SearchRequestParameters {

  @Pattern(regexp = "^(-?\\d+(\\.\\d+)?),(-?\\d+(\\.\\d+)?)$",
      message = "Coordinates must be provided in the format: 33.64,-117.93")
  private String near;

  private List<Integer> ids;

  @Valid
  private PriceSearchRequestParameters price;

}
