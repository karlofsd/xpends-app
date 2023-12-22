package com.soberk.xpends.spot.service;

import com.soberk.xpends.core.interfaces.TransactionOperations;
import com.soberk.xpends.spot.dto.request.SpotReqDto;
import com.soberk.xpends.spot.dto.response.SpotDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface SpotService {
    void create(SpotReqDto request) throws SQLException;
    void update(Long id, double amount, TransactionOperations operation) throws SQLException;
    void remove(Long id) throws SQLException;
    SpotDto get(Long id) throws SQLException;
    List<SpotDto> getAll(Map<String, Object> params) throws SQLException;

}
