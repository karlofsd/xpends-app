package com.soberk.xpends.transaction.repository;

import com.soberk.xpends.core.interfaces.CRUDRepository;
import com.soberk.xpends.transaction.domain.Transaction;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface TransactionRepository extends CRUDRepository<Transaction, UUID> {
    List<Transaction> getByParams(Map<String, Object> params) throws SQLException;

    List<Transaction> getBetween(LocalDateTime minDate, LocalDateTime maxDate) throws SQLException;
}
