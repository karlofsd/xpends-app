package com.soberk.xpends.core.exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String msg){
        super(msg);
    }
}
