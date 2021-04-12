package com.ExceptionHandling;

import com.logging.LoggingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler
{
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Autowired
    private LoggingService loggingService;




    @ExceptionHandler(AppExceptions.class)
    public final ResponseEntity<Object> scpExceptions(AppExceptions ex, WebRequest request) {
        String errorMessage=ex.getErrorMsg();
        String details=loggingService.getExceptionDetails(ex);
        ex.printStackTrace();
        ErrorResponse error = new ErrorResponse(errorMessage, details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }




    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> defaultHandler(Exception ex, WebRequest request) {
        String errorMessage=ex.getLocalizedMessage();
        String details = loggingService.getExceptionDetails(ex);
        ex.printStackTrace();
        ErrorResponse error = new ErrorResponse(errorMessage, details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}