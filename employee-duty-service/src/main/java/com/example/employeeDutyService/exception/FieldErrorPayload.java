package com.example.employeeDutyService.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FieldErrorPayload {
    private Integer status;
    private String message;
    private List<FieldError> fieldErrors;

    public void addFieldError(String objectName, String path, String message) {
        FieldError error = new FieldError(objectName, path, message);
        fieldErrors.add(error);
    }
}
