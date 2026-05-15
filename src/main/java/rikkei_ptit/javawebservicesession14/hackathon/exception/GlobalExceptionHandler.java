package rikkei_ptit.javawebservicesession14.hackathon.exception;

import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.filters.AddDefaultCharsetFilter.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        ErrorMessage<Map<String, String>> errorMessage = new ErrorMessage<>(
            "Validation failed",
            HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()),
            System.currentTimeMillis(),
            errors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorMessage<String> errorMessage = new ErrorMessage<>(
            ex.getMessage(),
            HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()),
            System.currentTimeMillis(),
            ""
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
