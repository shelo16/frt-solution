package com.frt.pad.service.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FrtSuccess {

    OK("Success", HttpStatus.OK),
    UPDATED("Updated", HttpStatus.OK),
    CREATED("Created resource", HttpStatus.CREATED);

    private final String description;
    private final HttpStatus status;
}
