package com.soberk.xpends.account.service;

import com.soberk.xpends.account.domain.Account;
import com.soberk.xpends.account.dto.request.AccountReqDto;
import com.soberk.xpends.account.dto.request.AccountWithSpotReqDto;
import com.soberk.xpends.account.dto.response.AccountDto;
import com.soberk.xpends.account.dto.response.AccountWithSpotDto;
import com.soberk.xpends.account.dto.mapper.AccountResponseMapper;
import com.soberk.xpends.account.repository.AccountRepository;
import com.soberk.xpends.core.exceptions.EntityNotFoundException;
import com.soberk.xpends.spot.repository.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SpotRepository spotRepository;

    @Autowired
    private AccountResponseMapper responseMapper;

    @Override
    public void create(AccountWithSpotReqDto request) throws SQLException {
        Account acc = this.accountRepository.create(request.toEntity());
        request.getSpot().setAccountId(acc.getId());
        spotRepository.create(request.getSpot().toEntity());
    }

    @Override
    public void update(UUID id, AccountReqDto request) throws SQLException {
        Account acc = request.toEntity();
        acc.setId(id);
        this.accountRepository.update(acc);
    }

    @Override
    public void delete(UUID id) throws SQLException {
        this.accountRepository.delete(id);
    }

    @Override
    public AccountWithSpotDto get(UUID id) throws SQLException, EntityNotFoundException {
        Account acc = this.accountRepository.get(id);
        return responseMapper.toDtoWithSpot(acc);
    }

    @Override
    public List<AccountDto> getAll() throws SQLException {
        List<Account> list = this.accountRepository.getAll();
        return list.stream().map(acc -> responseMapper.toDto(acc)).toList();
    }
}
