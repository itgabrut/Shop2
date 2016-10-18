package com.ilya.jsrCustomValidators;

import com.ilya.dao.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

/**
 * Created by ilya on 01.10.2016.
 * validator
 */
@Component
public class MyCustomVal implements ConstraintValidator<Annotation,String> {

    @Autowired
    private ItemRepository repository;

    @Override
    public void initialize(Annotation annotation) {

    }

    /* called twice */
    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        if(repository == null) return true;
        return repository.isUniqueName(name);
    }
}
