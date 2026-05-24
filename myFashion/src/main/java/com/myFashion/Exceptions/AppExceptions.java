package com.myFashion.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptions {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> UserExpections(Exception ex) {

        Map<String,Object> errors = new HashMap<>();
        Map<String,Object> errors1 = new HashMap<>();
        errors.put("result","Failed");
        errors.put("message",ex.getMessage());
        errors1.put("response", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors1);
    }
}
