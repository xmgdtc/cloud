package org.xmgdtc.common.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.xmgdtc.api.response.RestfulResponse;
import org.xmgdtc.api.excetpion.CloudAuthException;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource;


    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({CloudAuthException.class})
    public RestfulResponse handleException(CloudAuthException e) {
        RestfulResponse response = new RestfulResponse(e.getErrCode(), e.getErrMsg(), e.getMessage());
        return response;
    }

}