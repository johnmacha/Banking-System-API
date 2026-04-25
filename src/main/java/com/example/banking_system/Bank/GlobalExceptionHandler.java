package com.example.banking_system.Bank;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<APIResponse<Object>> handleNotFound(ResourceNotFoundException ex){
    return ResponseEntity.status(404).body(
        new APIResponse<>("error", ex.getMessage(), null)
    );
}
@ExceptionHandler(Exception.class)
public ResponseEntity<APIResponse<Object>> handleGeneral(Exception ex){
    return ResponseEntity.status(500).body(
        new APIResponse<>("error", "Something went wrong", null)
    );
}
}
