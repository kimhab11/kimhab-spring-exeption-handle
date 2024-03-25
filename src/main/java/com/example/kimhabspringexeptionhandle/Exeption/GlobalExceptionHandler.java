package com.example.kimhabspringexeptionhandle.Exeption;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice //  mark the class as a global exception handler.
@RestController
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<Object> handleNull(NullPointerException ex, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "NullPointerException",
                ex.getMessage(),
                request.getDescription(true));
        log.error(String.valueOf(errorResponse));
        return handleExceptionInternal(ex,errorResponse, new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
    }
    @ExceptionHandler(CustomResourceNotFoundException.class) // custom exception class
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Resource not found",
                ex.getMessage(),
                request.getDescription(true));
        log.error(String.valueOf(errorResponse));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class) // define methods that handle specific types of exceptions.
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An Exception error occurred", ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({RuntimeException.class}) // define methods that handle RuntimeException
    public ResponseEntity<ErrorResponse> handleGlobalRuntimeException(RuntimeException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "A RuntimeException -> " + ex.getClass().getName() + " error occurred", ex.getMessage(),
                request.getDescription(true));
        var er = ex.fillInStackTrace();
        log.error("Class::-> {}", er.getClass());
        log.error("Cause::-> {}", er.getCause());
        log.error("Msg::-> {}", er.getMessage());
        log.error("StackTrace::-> {}", er.getStackTrace());
        log.error("Localized Msg::-> {}", er.getLocalizedMessage());
        log.error("Suppressed::-> {}", er.getSuppressed());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
