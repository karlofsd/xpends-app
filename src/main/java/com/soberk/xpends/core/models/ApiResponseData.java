package com.soberk.xpends.core.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseData<T> extends ApiResponse{
    private T data;

    public ApiResponseData(){

    }

    public ApiResponseData(Integer code, String message, T data) {
        super(code, message);
        this.data = data;
    }
}
