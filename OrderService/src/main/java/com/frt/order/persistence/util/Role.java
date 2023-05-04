package com.frt.order.persistence.util;

import com.frt.order.exception.model.customexception.GeneralException;
import com.frt.order.exception.util.FrtError;
import lombok.Getter;

@Getter
public enum Role {

    ADMIN,
    SELLER,
    CLIENT;


    public static Role getRole(String role) {
        return switch (role.toUpperCase()) {
            case "ADMIN" -> Role.ADMIN;
            case "SELLER" -> Role.SELLER;
            case "CLIENT" -> Role.CLIENT;
            default -> throw new GeneralException(FrtError.ROLE_NOT_FOUND);
        };
    }

}