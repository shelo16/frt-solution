package com.frt.pad.exception.model.customexception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GeneralExceptionResponse {

    private String generalMessage;
    private List<String> errors;

}
