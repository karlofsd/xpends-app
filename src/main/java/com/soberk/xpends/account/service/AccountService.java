package com.soberk.xpends.account.service;

import com.soberk.xpends.account.dto.request.AccountReqDto;
import com.soberk.xpends.account.dto.request.AccountWithSpotReqDto;
import com.soberk.xpends.account.dto.response.AccountDto;
import com.soberk.xpends.account.dto.response.AccountWithSpotDto;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface AccountService {
    void create(AccountWithSpotReqDto request) throws SQLException;
    void update(UUID id, AccountReqDto request) throws SQLException;
    void delete(UUID id) throws SQLException;

    AccountWithSpotDto get(UUID id) throws SQLException;
    List<AccountDto> getAll() throws SQLException;
}
