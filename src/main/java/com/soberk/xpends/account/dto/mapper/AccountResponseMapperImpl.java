package com.soberk.xpends.account.dto.mapper;

import com.soberk.xpends.account.domain.Account;
import com.soberk.xpends.account.dto.response.AccountDto;
import com.soberk.xpends.account.dto.response.AccountWithSpotDto;
import com.soberk.xpends.spot.dto.mapper.SpotResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountResponseMapperImpl implements AccountResponseMapper {
    @Autowired
    private SpotResponseMapper spotResponseMapper;

    @Override
    public AccountDto toDto(Account account){
        AccountDto dto = new AccountDto();
        dto.setId(account.getId());
        dto.setName(account.getName());
        dto.setDescription(account.getDescription());
        return dto;
    }

    @Override
    public AccountWithSpotDto toDtoWithSpot(Account account) {
        AccountWithSpotDto dto = new AccountWithSpotDto();
        dto.setId(account.getId());
        dto.setName(account.getName());
        dto.setDescription(account.getDescription());
        dto.setSpot(account.getSpot().stream().map(spot->spotResponseMapper.toDto(spot)).toList());
        return dto;
    }
}
