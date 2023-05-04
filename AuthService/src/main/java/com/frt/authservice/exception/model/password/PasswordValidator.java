package com.frt.authservice.exception.model.password;

import com.frt.authservice.exception.model.customexception.GeneralException;
import com.frt.authservice.exception.util.FrtError;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    /**
        Regex constraint :
            * Contains at least one digit, lowercase letter, uppercase letter, and special character
            * Does not contain any whitespace characters
            * Is between 8 and 20 characters long
     */
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        boolean isValid = password != null && password.matches(PASSWORD_REGEX);
        if (!isValid) {
            throw new GeneralException(FrtError.BAD_PASSWORD);
        }
        return true;
    }

    public static void isValidPasswordGlobalUse(String password) {
        boolean isValid = password != null && password.matches(PASSWORD_REGEX);
        if (!isValid) {
            throw new GeneralException(FrtError.BAD_PASSWORD);
        }
    }
}
