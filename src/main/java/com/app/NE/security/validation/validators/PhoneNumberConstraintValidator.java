package com.app.NE.security.validation.validators;

import com.app.NE.security.validation.annotations.ValidPhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberConstraintValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;

        // Regex pattern to match 078xxxxxxx, 079xxxxxxx, 072xxxxxxx, or 073xxxxxxx
        String regex = "^(078|079|072|073)\\d{7}$";

        return value.matches(regex);
    }
}
