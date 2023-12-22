package com.soberk.xpends.console.enums;

public enum TransactionType {
    DEPOSIT(1L, "DEPOSIT"),EXPENSE(2L,"EXPENSE");

    private final Long value;
    private final String name;

    TransactionType(Long value,String name){
        this.value = value;
        this.name = name;
    }

    public Long value(){
        return this.value;
    }
}


