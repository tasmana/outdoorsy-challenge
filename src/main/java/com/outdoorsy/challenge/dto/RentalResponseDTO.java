package com.outdoorsy.challenge.dto;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.outdoorsy.challenge.domain.RentalImage;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@JsonAutoDetect(fieldVisibility = ANY)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
@NoArgsConstructor
@Accessors(fluent = true)
public class RentalResponseDTO {

  
  private Integer id;

  private String name;

  private String type;
  
  private String description;
  
  private int sleeps;
  
  private long pricePerDay;
  
  private String homeCity;
  
  private String homeState;
  
  private String homeZip;
  
  private String homeCounty;
  
  private String homeCountry;
  
  private String vehicleMake;
  
  private String vehicleModel;
  
  private int vehicleYear;
  
  private BigDecimal vehicleLength;
  
  private Instant created;
  
  private Instant updated;
  
  private double lat;
  
  private double lng;
  
  private String primaryImageUrl;
  
  private String ownerName;
  
  private String ownerAvatarUrl;

  private List<RentalImage> images;
}
