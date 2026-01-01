package com.week2.homework.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {PasswordValidator.class})
public @interface PasswordValidation {

    String message() default "Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 special character, and be at least 10 characters long.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
