package com.example.employeeDutyService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@ControllerAdvice
public class APIExceptionHandler {
    @ExceptionHandler(value = {EmployeeDutyNotFoundException.class})
    public ResponseEntity<ApiExceptionPayload> handleInvalidBoardSizeException(EmployeeDutyNotFoundException ex) {
        ApiExceptionPayload apiExceptionPayload = new ApiExceptionPayload(
                HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), ex.getMessage(), ex, LocalDateTime.now()
        );

        return new ResponseEntity<>(apiExceptionPayload, BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<FieldErrorPayload> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return new ResponseEntity<>(processFieldErrors(fieldErrors), BAD_REQUEST);
    }

    private FieldErrorPayload processFieldErrors(List<FieldError> fieldErrors) {
        var error = FieldErrorPayload.builder()
                .status(BAD_REQUEST.value())
                .fieldErrors(new ArrayList<>())
                .message("validation error")
                .build();
        for (FieldError fieldError : fieldErrors) {
            error.addFieldError(fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
        }
        return error;
    }
}