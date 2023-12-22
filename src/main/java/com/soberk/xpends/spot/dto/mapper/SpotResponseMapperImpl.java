package com.soberk.xpends.spot.dto.mapper;

import com.soberk.xpends.currency.dto.mapper.CurrencyResponseMapper;
import com.soberk.xpends.spot.domain.Spot;
import com.soberk.xpends.spot.dto.response.SpotDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpotResponseMapperImpl implements SpotResponseMapper{
    @Autowired
    private CurrencyResponseMapper currencyResponseMapper;

    @Override
    public SpotDto toDto(Spot entity) {
        SpotDto spotDTO = new SpotDto();
        spotDTO.setId(entity.getId());
        spotDTO.setAmount(entity.getAmount());
        spotDTO.setCurrency(currencyResponseMapper.toDto(entity.getCurrency()));
        return spotDTO;
    }
}
