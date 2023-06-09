package com.app.lot_2.web.advice;

import com.app.lot_2.web.advice.response.CommonErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class GlobalErrorHandler {
    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<CommonErrorResponse> handleValidationErrors(HttpMessageConversionException err) {
        err.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new CommonErrorResponse(
                        HttpStatus.BAD_REQUEST.value(), err.getMessage())
                );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<CommonErrorResponse> handleValidationErrors(HttpRequestMethodNotSupportedException err) {
        err.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new CommonErrorResponse(
                        HttpStatus.BAD_REQUEST.value(), "wrong method")
                );
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<CommonErrorResponse> handleValidationErrors(PropertyReferenceException err) {
        err.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new CommonErrorResponse(
                        HttpStatus.BAD_REQUEST.value(), "property not supported")
                );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CommonErrorResponse> handleValidationErrors(HttpMessageNotReadableException err) {
        err.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new CommonErrorResponse(
                        HttpStatus.BAD_REQUEST.value(), "not a valid json schema")
                );
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<CommonErrorResponse> handleValidationErrors(BindException err) {
        err.printStackTrace();
        FieldError fieldError = Optional.ofNullable(err.getFieldError())
                .orElse(new FieldError("error", "error", err.getMessage()));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new CommonErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        fieldError.getField() + " : " + fieldError)
                );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CommonErrorResponse> handleError(EntityNotFoundException err) {
        err.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new CommonErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        err.getMessage()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonErrorResponse> handleError(Exception err) {
        err.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new CommonErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "something went wrong"
                ));
    }
}
