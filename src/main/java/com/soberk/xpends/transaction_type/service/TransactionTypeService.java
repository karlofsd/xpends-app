package com.soberk.xpends.transaction_type.service;

import com.soberk.xpends.transaction_type.dto.request.TransactionTypeReqDto;
import com.soberk.xpends.transaction_type.dto.response.TransactionTypeDto;

import java.sql.SQLException;
import java.util.List;

public interface TransactionTypeService {
    void create (TransactionTypeReqDto request) throws SQLException;
    void update (Long id, TransactionTypeReqDto request) throws SQLException;
    void remove(Long id) throws SQLException;
    TransactionTypeDto getById(Long id) throws SQLException;
    List<TransactionTypeDto> getAll() throws SQLException;

}
