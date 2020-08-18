package com.outdoorsy.challenge.repository;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RentalFilters {

  private NearFilter near;

  private List<Integer> ids;

  private PriceFilter price;

  @Data
  @NoArgsConstructor
  public static class NearFilter {

    private String lat;

    private String lng;
  }

  @Data
  @NoArgsConstructor
  public static class PriceFilter {

    private String min;

    private String max;
  }
}
