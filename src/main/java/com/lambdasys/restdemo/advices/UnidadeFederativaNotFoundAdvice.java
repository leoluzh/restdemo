package com.lambdasys.restdemo.advices;

import com.lambdasys.restdemo.exceptions.UnidadeFederativaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * leoluzh
 * @since 25/07/2020
 * @version 0.1
 */

@ControllerAdvice
public class UnidadeFederativaNotFoundAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UnidadeFederativaNotFoundException.class)
    public String handleError(UnidadeFederativaNotFoundException ex ){
        return ex.getMessage();
    }

}
