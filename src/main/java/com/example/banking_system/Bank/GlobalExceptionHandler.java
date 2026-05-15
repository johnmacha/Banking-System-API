package com.example.banking_system.Bank;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(MethodArgumentNotValidException.class)
public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex){
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getFieldErrors().forEach(error ->
        errors.put(error.getField(), error.getDefaultMessage())
    );
    return errors;
}
// public ResponseEntity<APIResponse<Object>> handleValidation(MethodArgumentNotValidException ex){
   
//     String errorMessage = ex.getBindingResult()
//    .getFieldErrors()
//    .stream()
//     .map(error -> error.getDefaultMessage())
//     .findFirst()
//     .orElse("Validation failed");
   
//     return ResponseEntity.badRequest().body(
//         new APIResponse<>("error",errorMessage, null)
//     );
// }
}

