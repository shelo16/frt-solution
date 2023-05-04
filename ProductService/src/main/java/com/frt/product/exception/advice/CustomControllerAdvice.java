package com.frt.product.exception.advice;

import com.frt.product.exception.model.customexception.GeneralException;
import com.frt.product.exception.model.customexception.GeneralExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class CustomControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralExceptionResponse> handleMethodArgumentException(MethodArgumentNotValidException e) {
        log.error("Error in validating method arguments");
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new GeneralExceptionResponse(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<?> handleCustomException(GeneralException e) {
        log.error(e.getFrtError().getDescription());
        return new ResponseEntity<>(new GeneralExceptionResponse(List.of(e.getFrtError().getDescription())), e.getFrtError().getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception e) {
        return new ResponseEntity<>(new GeneralExceptionResponse(List.of(e.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
