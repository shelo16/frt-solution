package com.frt.authservice.exception.model.phone;

import com.frt.authservice.exception.model.customexception.GeneralException;
import com.frt.authservice.exception.util.FrtError;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {

    /**
     * Regex constraint :
     * Contains at least one digit, lowercase letter, uppercase letter, and special character
     * Does not contain any whitespace characters
     * Is between 8 and 20 characters long
     */
    private static final String PHONE_NUMBER_REGEX = "^[1-9][0-9]{8}$";

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        boolean isValid = phoneNumber != null && phoneNumber.matches(PHONE_NUMBER_REGEX);
        if (!isValid) {
            throw new GeneralException(FrtError.BAD_PHONE_NUMBER);
        }
        return true;
    }
}
