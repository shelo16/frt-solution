package com.frt.product.exception.model.customexception;

import com.frt.product.exception.util.FrtError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class GeneralException extends RuntimeException {

    private final FrtError frtError;

}
