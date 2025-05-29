package com.app.NE.security.validation.annotations;

import com.app.NE.security.validation.validators.NationalIdConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NationalIdConstraintValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidNationalId {
    String message() default "Invalid national id";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
