package com.outdoorsy.challenge.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.outdoorsy.challenge.dto.ErrorDTO;
import com.outdoorsy.challenge.exception.RentalNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RestErrorHandler {

  @ExceptionHandler(value = BindException.class)
  public ResponseEntity<ErrorDTO> handleException(BindException ex, HttpServletRequest request) {
    return new ResponseEntity<>(new ErrorDTO(HttpStatus.BAD_REQUEST, request, ex.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = RentalNotFoundException.class)
  public ResponseEntity<ErrorDTO> handleException(RentalNotFoundException ex, HttpServletRequest request) {
    return new ResponseEntity<>(new ErrorDTO(HttpStatus.NOT_FOUND, request, ex.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = Throwable.class)
  public ResponseEntity<ErrorDTO> handleException(Throwable ex, HttpServletRequest request) {
    log.error("Unexpected exception: ", ex);
    return new ResponseEntity<>(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, request, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
