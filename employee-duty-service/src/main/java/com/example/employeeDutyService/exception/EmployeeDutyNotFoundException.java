package com.example.employeeDutyService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeDutyNotFoundException extends RuntimeException{
    public EmployeeDutyNotFoundException() {
    }

    public EmployeeDutyNotFoundException(String message) {
        super(message);
    }

    public EmployeeDutyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeDutyNotFoundException(Throwable cause) {
        super(cause);
    }

    public EmployeeDutyNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
