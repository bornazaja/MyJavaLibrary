package com.bzaja.myjavalibrary.validation.customconstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {

    @Override
    public void initialize(StrongPassword a) {

    }

    @Override
    public boolean isValid(String lozinka, ConstraintValidatorContext cvc) {
        return lozinka != null && lozinka.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,})$");
    }

}
