package com.outdoorsy.challenge.validation;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OneFilterAllowedValidator implements ConstraintValidator<OneFilterAllowed, Object> {

  @Override
  public void initialize(OneFilterAllowed annotation) {
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext ctx) {
    try {
      boolean appliedFilter = false;
      Field[] fields = value.getClass().getDeclaredFields();
      for (Field field : fields) {
        field.setAccessible(true);
        Object fieldValue = field.get(value);
        if (fieldValue != null) {
          if (appliedFilter) {
            return false;
          }

          appliedFilter = true;
        }
      }
    } catch (IllegalAccessException ignored) {
    }

    return true;
  }

}