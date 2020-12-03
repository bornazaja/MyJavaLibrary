package com.bzaja.myjavalibrary.validation.customconstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String>{

    @Override
    public void initialize(PhoneNumber a) {

    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext cvc) {
        return phoneNumber != null && phoneNumber.matches("\\d{8,10}");
    }

}
