package com.bartoszkaczmarek.recruitment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = HttpClientErrorException.class)
    ResponseStatusException handleMyRestTemplateException(HttpClientErrorException ex, HttpServletRequest request) {
        if (ex.getStatusCode().is4xxClientError()) {
            return new ResponseStatusException(ex.getStatusCode());
        }
        return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
