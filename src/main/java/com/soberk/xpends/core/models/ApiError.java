package com.soberk.xpends.core.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiError extends ApiResponse{
    private LocalDateTime dateTime = LocalDateTime.now();

    public ApiError() {
    }

    public ApiError(Integer code, String description) {
        super(code,description);
    }
}
