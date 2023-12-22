package com.soberk.xpends.core.exceptions;

import com.soberk.xpends.core.models.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@ControllerAdvice
public class RestControllerException {
    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleEntityNotFoundException(){
        ApiError error = new ApiError();
        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage("Entity was not found");
        return error;
    }
}
