package com.bzaja.myjavalibrary.validation.customconstraint;

import java.time.LocalDate;
import java.time.Period;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<Age, LocalDate> {

    protected int minDob;
    protected int maxDob;

    @Override
    public void initialize(Age dob) {
        this.minDob = dob.min();
        this.maxDob = dob.max();
    }

    @Override
    public boolean isValid(LocalDate datumRodjenja, ConstraintValidatorContext cvc) {
        if (datumRodjenja == null) {
            return false;
        }
        int godine = Period.between(datumRodjenja, LocalDate.now()).getYears();
        return godine >= minDob && godine <= maxDob;
    }

}
