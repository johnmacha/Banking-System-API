package com.example.banking_system.Bank;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<APIResponse<Object>> handleValidation(MethodArgumentNotValidException ex){
   
    String errorMessage = ex.getBindingResult()
   .getFieldErrors()
   .stream()
    .map(error -> error.getDefaultMessage())
    .findFirst()
    .orElse("Validation failed");
   
    return ResponseEntity.badRequest().body(
        new APIResponse<>("error",errorMessage, null)
    );
}
}
// @ExceptionHandler(Exception.class)
// public ResponseEntity<APIResponse<Object>> handleGeneral(Exception ex){
//     return ResponseEntity.status(500).body(
//         new APIResponse<>("error", "Something went wrong", null)
//     );
// }
// }
