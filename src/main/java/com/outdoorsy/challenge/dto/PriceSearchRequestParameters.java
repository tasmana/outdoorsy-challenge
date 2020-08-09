package com.outdoorsy.challenge.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PriceSearchRequestParameters {

  @NotNull
  private Long min;
  @NotNull
  private Long max;
}
