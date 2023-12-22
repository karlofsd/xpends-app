package com.soberk.xpends.currency.dto.mapper;

import com.soberk.xpends.currency.domain.Currency;
import com.soberk.xpends.currency.dto.response.CurrencyDto;

public interface CurrencyResponseMapper {
    CurrencyDto toDto(Currency currency);
}
