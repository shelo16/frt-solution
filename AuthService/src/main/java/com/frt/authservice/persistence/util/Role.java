package com.frt.authservice.persistence.util;

import com.frt.authservice.exception.model.customexception.GeneralException;
import com.frt.authservice.exception.util.FrtError;
import lombok.Getter;

@Getter
public enum Role {

    ADMIN,
    SELLER,
    CLIENT;


    public static Role getRole(String role) {
        switch (role.toUpperCase()) {
            case "ADMIN":
                return Role.ADMIN;
            case "SELLER":
                return Role.SELLER;
            case "CLIENT":
                return Role.CLIENT;
            default:
                throw new GeneralException(FrtError.ROLE_NOT_FOUND);
        }
    }

}