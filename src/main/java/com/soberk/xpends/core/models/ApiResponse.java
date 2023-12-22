package com.soberk.xpends.core.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    private Integer code;
    private String message;

    public ApiResponse() {
    }

    public ApiResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
