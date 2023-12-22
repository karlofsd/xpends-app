package com.soberk.xpends.transaction.service;

import com.soberk.xpends.transaction.dto.request.TransactionReqDto;
import com.soberk.xpends.transaction.dto.response.TransactionDto;
import com.soberk.xpends.transaction.dto.response.TransactionWithCategoryDto;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface TransactionService {
    /**
     * @param request
     * @throws SQLException
     */
    void create(TransactionReqDto request) throws SQLException;

    /**
     * @param id
     * @throws SQLException
     */
    void delete(UUID id) throws SQLException;

    /**
     * @param id
     * @return
     * @throws SQLException
     */
    TransactionWithCategoryDto get(UUID id) throws SQLException;

    /**
     * @param params
     * @return
     * @throws SQLException
     */
    List<TransactionDto> getAll(Map<String, Object> params) throws SQLException;

    List<TransactionDto> getByDate(LocalDateTime minDate, LocalDateTime maxDate) throws SQLException;
}
