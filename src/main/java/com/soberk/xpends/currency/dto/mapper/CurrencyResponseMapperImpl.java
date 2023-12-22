package com.soberk.xpends.currency.dto.mapper;

import com.soberk.xpends.currency.domain.Currency;
import com.soberk.xpends.currency.dto.response.CurrencyDto;
import org.springframework.stereotype.Component;

@Component
public class CurrencyResponseMapperImpl implements CurrencyResponseMapper{

    @Override
    public CurrencyDto toDto(Currency currency) {
        CurrencyDto dto = new CurrencyDto();
        dto.setId(currency.getId());
        dto.setName(currency.getName());
        dto.setCode(currency.getCode());
        dto.setSymbol(currency.getSymbol());
        dto.setExchange(currency.getExchange());
        dto.setDeleted(currency.isDeleted());
        return dto;
    }
}
