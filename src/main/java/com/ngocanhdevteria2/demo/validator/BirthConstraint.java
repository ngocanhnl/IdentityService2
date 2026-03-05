package com.ngocanhdevteria2.demo.validator;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

// Chi validate 1 field trong
@Target({ElementType.FIELD})
// Chay khi nao, co the la compile time
@Retention(RetentionPolicy.RUNTIME)
// Class chiu trach nhiem validation
@Constraint(validatedBy = {BirthValidator.class})
public @interface BirthConstraint {
    String message() default "Invalid birth date";

    int min();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
