package com.soberk.xpends.currency.service;

import com.soberk.xpends.currency.dto.request.CurrencyReqDto;
import com.soberk.xpends.currency.dto.response.CurrencyDto;

import java.sql.SQLException;
import java.util.List;

public interface CurrencyService {
    void create(CurrencyReqDto request) throws SQLException;

    void update(Long id, CurrencyReqDto request) throws SQLException;

    void remove(Long id) throws SQLException;

    CurrencyDto getById(Long id) throws SQLException;

    List<CurrencyDto> getAll() throws SQLException;
}
