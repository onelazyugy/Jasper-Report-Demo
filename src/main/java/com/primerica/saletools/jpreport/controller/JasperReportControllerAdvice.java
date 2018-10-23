package com.primerica.saletools.jpreport.controller;

import com.primerica.saletools.jpreport.exception.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@ControllerAdvice
public class JasperReportControllerAdvice {

    @ExceptionHandler(ApplicationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleJasperReportException(ApplicationException ae) {
        return ae.getMessage();
    }

}
