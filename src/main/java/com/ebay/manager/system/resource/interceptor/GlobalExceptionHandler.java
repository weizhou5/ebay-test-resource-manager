package com.ebay.manager.system.resource.interceptor;

import com.ebay.manager.system.resource.execption.BaseException;
import com.ebay.manager.system.resource.vo.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Response> businessException(BaseException e){
        Response response = Response.getResponse(e.getCode(), e.getMessage());
        return ResponseEntity.ok(response);
    }
}
