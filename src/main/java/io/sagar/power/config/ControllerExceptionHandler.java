package io.sagar.power.config;

import io.sagar.power.dto.MessageResponseDTO;
import io.sagar.power.utils.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {


    // Bean validation error controller advice
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<MessageResponseDTO<?>> handleGeneralExceptions(Exception ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return ResponseUtils.responseGenerator(getErrorsMap(errors), false, HttpStatus.BAD_REQUEST);
    }


    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
