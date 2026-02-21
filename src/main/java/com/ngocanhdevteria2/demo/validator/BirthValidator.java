package com.ngocanhdevteria2.demo.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class BirthValidator implements ConstraintValidator<BirthConstraint, LocalDate> {

    private int min;

    //Ham xu ly data co dung hay khong
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {

        if(Objects.isNull(localDate)) return true;

        long years = ChronoUnit.YEARS.between(localDate, LocalDate.now());



        return years >= min;
    }
    //init khi khoi tao, lay thong tin cua constraint
    @Override
    public void initialize(BirthConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
    }
}
