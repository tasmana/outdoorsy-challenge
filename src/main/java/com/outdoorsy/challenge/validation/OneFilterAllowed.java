package com.outdoorsy.challenge.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = OneFilterAllowedValidator.class)
@Target({ TYPE })
@Retention(RUNTIME)
public @interface OneFilterAllowed {

  String message() default "Only one filter is allowed!";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

}
