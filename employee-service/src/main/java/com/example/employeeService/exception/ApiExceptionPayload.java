package com.example.employeeService.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ApiExceptionPayload(HttpStatus error,
                                  int errorCode,
                                  String message,
                                  Throwable throwable,
                                  LocalDateTime timeStamp) {
}
