// package com.shipdist.app.exception;

// import com.shipdist.app.dto.ErrorResponse;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestControllerAdvice;

// import java.time.LocalDateTime;
// import java.util.stream.Collectors;

// @RestControllerAdvice
// public class GlobalExceptionHandler {

//     @ExceptionHandler(ResourceNotFoundException.class)
//     public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
//         return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                 .body(new ErrorResponse(LocalDateTime.now(), 404, ex.getMessage()));
//     }

//     @ExceptionHandler(BadCredentialsException.class)
//     public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException ex) {
//         return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                 .body(new ErrorResponse(LocalDateTime.now(), 401, "Invalid email or password"));
//     }

//     @ExceptionHandler(IllegalArgumentException.class)
//     public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
//         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                 .body(new ErrorResponse(LocalDateTime.now(), 400, ex.getMessage()));
//     }

//     @ExceptionHandler(MethodArgumentNotValidException.class)
//     public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
//         String message = ex.getBindingResult().getFieldErrors().stream()
//                 .map(err -> err.getField() + ": " + err.getDefaultMessage())
//                 .collect(Collectors.joining(", "));
//         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                 .body(new ErrorResponse(LocalDateTime.now(), 400, message));
//     }
    
//     @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//     public ResponseEntity<ErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
//     Map<String, Object> body = new HashMap<>();
//     body.put("timestamp", LocalDateTime.now());
//     body.put("status", HttpStatus.METHOD_NOT_ALLOWED.value());
//     body.put("message", "Request method '" + ex.getMethod() + "' is not supported for this endpoint");
//     return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
//         .body(body);
// }

//     @ExceptionHandler(Exception.class)
//     public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                 .body(new ErrorResponse(LocalDateTime.now(), 500, "Something went wrong: " + ex.getMessage()));
//     }
// }

package com.shipdist.app.exception;

import com.shipdist.app.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(LocalDateTime.now(), 404, ex.getMessage()));
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(LocalDateTime.now(), 401, "Invalid email or password"));
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(LocalDateTime.now(), 400, ex.getMessage()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(LocalDateTime.now(), 400, message));
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new ErrorResponse(LocalDateTime.now(), 405,
                        "Request method '" + ex.getMethod() + "' is not supported for this endpoint"));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(LocalDateTime.now(), 500, "Something went wrong: " + ex.getMessage()));
    }
}
