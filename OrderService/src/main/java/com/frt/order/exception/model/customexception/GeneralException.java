package com.frt.order.exception.model.customexception;

import com.frt.order.exception.util.FrtError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class GeneralException extends RuntimeException {

    private final FrtError frtError;

}
