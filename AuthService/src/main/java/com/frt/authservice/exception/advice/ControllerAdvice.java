package com.frt.authservice.exception.advice;

import com.frt.authservice.exception.model.customexception.GeneralException;
import com.frt.authservice.exception.model.customexception.GeneralExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<?> handleCustomException(GeneralException e) {
        log.error(e.getFrtError().getDescription());
        return new ResponseEntity<>(new GeneralExceptionResponse(List.of(e.getFrtError().getDescription())), e.getFrtError().getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GeneralExceptionResponse handleMethodArgumentException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return new GeneralExceptionResponse(errors);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handleGlobalException(Exception e) {
//        return new ResponseEntity<>(new GeneralExceptionResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

}
