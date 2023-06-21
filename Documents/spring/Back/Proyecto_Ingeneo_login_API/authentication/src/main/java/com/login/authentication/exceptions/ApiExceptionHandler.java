package com.login.authentication.exceptions;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value= {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        HttpStatus badRequest = HttpStatus.NOT_FOUND;
        ApiException apiException=	new ApiException(
                e.getMessage(),
                badRequest,
                e,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value= {ApiRequestExceptionValid.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestExceptionValid e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException=	new ApiException(
                e.getMessage(),
                badRequest,
                e,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Map<String, String> handleApiRequestException(MethodArgumentTypeMismatchException ex){

        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("message", "La ruta ingresada no es valida");

        return errorMap;
    }
}
