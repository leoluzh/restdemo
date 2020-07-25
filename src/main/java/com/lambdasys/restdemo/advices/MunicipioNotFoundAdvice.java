package com.lambdasys.restdemo.advices;

import com.lambdasys.restdemo.exceptions.MunicipioNotFoundException;
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
public class MunicipioNotFoundAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MunicipioNotFoundException.class)
    public String municipioNotFoundHandler( MunicipioNotFoundException ex ){
        return ex.getMessage();
    }

}
