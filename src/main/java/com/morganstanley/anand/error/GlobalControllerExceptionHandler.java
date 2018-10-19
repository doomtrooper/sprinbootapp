package com.morganstanley.anand.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NullPointerException.class, EntityNotFoundException.class})
    @ResponseBody
    public GenericException failure(Exception exp){
        return new GenericException(exp.getMessage(), 400);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public GenericException handleAnyException(NoHandlerFoundException exp, HttpServletResponse response) {
        return new GenericException(exp.getMessage(), response.getStatus());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public GenericException handleThrowable(final Throwable ex, final HttpServletResponse response) {
        return new GenericException(ex.getMessage(), response.getStatus());
    }
}
