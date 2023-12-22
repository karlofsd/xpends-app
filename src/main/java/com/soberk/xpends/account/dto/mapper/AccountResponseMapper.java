package com.soberk.xpends.account.dto.mapper;

import com.soberk.xpends.account.domain.Account;
import com.soberk.xpends.account.dto.response.AccountDto;
import com.soberk.xpends.account.dto.response.AccountWithSpotDto;

public interface AccountResponseMapper {
    AccountDto toDto(Account account);
    AccountWithSpotDto toDtoWithSpot(Account account);
}
