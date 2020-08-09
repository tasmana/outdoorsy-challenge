package com.outdoorsy.challenge.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchRequestParameters {

  private String near;

  private List<Integer> ids;

  private PriceSearchRequestParameters price;

}
