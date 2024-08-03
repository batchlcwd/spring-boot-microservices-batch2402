package com.boot.jpa.ecom.exception;

import com.boot.jpa.ecom.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ResourceNotFoundException ex) {
        System.out.println(ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Error occurred " + ex.getMessage());
        errorResponse.setSuccess(false);
        errorResponse.setStatus(HttpStatus.NOT_FOUND);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);


    }


}
