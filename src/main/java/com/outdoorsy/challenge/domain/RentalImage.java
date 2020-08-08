package com.outdoorsy.challenge.domain;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;

@Entity
@Table(name = "rental_images")
@Data
public class RentalImage {

  @Id
  @GenericGenerator(name = "RentalImage",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "rental_images_seq")
      })
  @GeneratedValue(strategy = SEQUENCE, generator = "RentalImage")
  @Column(updatable = false)
  private Integer id;

  private String url;
}
