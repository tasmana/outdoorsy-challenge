package com.outdoorsy.challenge.exception;

public class RentalNotFoundException extends Exception {

  public RentalNotFoundException(Integer id) {
    super(String.format("Rental with id %d was not found!", id));
  }
}
