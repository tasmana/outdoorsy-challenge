package com.outdoorsy.challenge.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.Lists;
import com.outdoorsy.challenge.dto.SearchRequestParameters;

@ExtendWith(MockitoExtension.class)
public class OneFilterAllowedValidatorUnitTest {

  @Mock
  private ConstraintValidatorContext constraintValidatorContext;

  private OneFilterAllowedValidator oneFilterAllowedValidator = new OneFilterAllowedValidator();

  @Test
  public void testNoFiltersApplied() {
    SearchRequestParameters searchRequestParameters = new SearchRequestParameters();

    boolean result = oneFilterAllowedValidator.isValid(searchRequestParameters, constraintValidatorContext);

    assertTrue(result);
  }

  @Test
  public void testOneFilterApplied() {
    SearchRequestParameters searchRequestParameters = new SearchRequestParameters();
    searchRequestParameters.setIds(Lists.newArrayList(1));

    boolean result = oneFilterAllowedValidator.isValid(searchRequestParameters, constraintValidatorContext);

    assertTrue(result);
  }

  @Test
  public void testMoreThanOneFilterApplied() {
    SearchRequestParameters searchRequestParameters = new SearchRequestParameters();
    searchRequestParameters.setIds(Lists.newArrayList(1));
    searchRequestParameters.setNear("33.64,-117.93");

    boolean result = oneFilterAllowedValidator.isValid(searchRequestParameters, constraintValidatorContext);

    assertFalse(result);
  }
}
