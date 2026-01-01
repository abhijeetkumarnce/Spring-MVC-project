package com.week2.homework.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Min;

import java.util.List;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {

    // Regex explanation:
    // (?=.*[A-Z])     -> at least one uppercase letter
    // (?=.*[a-z])     -> at least one lowercase letter
    // (?=.*[@$!%*?&]) -> at least one special character
    // .{10,}          -> minimum 10 characters

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*[@$!%*?&]).{10,}$");

    @Override
    public boolean isValid(String inputPassword, ConstraintValidatorContext constraintValidatorContext) {
        if(inputPassword == null)
            return false;

        return PASSWORD_PATTERN.matcher(inputPassword).matches();
    }
}
