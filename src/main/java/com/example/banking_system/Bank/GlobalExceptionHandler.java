package com.example.banking_system.Bank;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExceptionHandler {

@ResponseStatus(HttpStatus.BAD_REQUEST)
@ExceptionHandler(MethodArgumentNotValidException.class)
public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex){
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getFieldErrors().forEach(error ->
        errors.put(error.getField(), error.getDefaultMessage())
    );
    return errors;
}

@ExceptionHandler(InvalidAmountException.class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public Map<String, String> handleInvalidAmount(InvalidAmountException ex){
    return Map.of("error", ex.getMessage());
}

@ExceptionHandler(ResourceNotFoundException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
public Map<String, String> handleResourceNotFound(ResourceNotFoundException ex){
    return Map.of("error", ex.getMessage());
}

@ExceptionHandler(DateTimeParseException.class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
Map<String, String>  handleDateTimeParseException(DateTimeParseException ex){

return Map.of(
    "error",
    "Invalid date format. Use yyyy-MM-ddTHH:mm:ss"
);
}

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


