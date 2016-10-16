package com.ilya.jsrCustomValidators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by ilya on 01.10.2016.
 */
@Documented
@Constraint(validatedBy = MyCustomVal.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @ interface UniqueName {
    String message() default "Name  not unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
