/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bzaja.myjavalibrary.springframework.factory;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

/**
 *
 * @author Borna
 */
public final class HibernateValidatorFactory {
    
    private HibernateValidatorFactory(){
        
    }
    
    public static Validator get(AutowireCapableBeanFactory autowireCapableBeanFactory) {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .constraintValidatorFactory(new SpringConstraintValidatorFactory(autowireCapableBeanFactory))
                .buildValidatorFactory();

        return validatorFactory.getValidator();
    }
}
