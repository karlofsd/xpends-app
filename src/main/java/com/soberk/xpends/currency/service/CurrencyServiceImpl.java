package com.soberk.xpends.currency.service;

import com.soberk.xpends.core.exceptions.EntityNotFoundException;
import com.soberk.xpends.currency.domain.Currency;
import com.soberk.xpends.currency.dto.request.CurrencyReqDto;
import com.soberk.xpends.currency.dto.response.CurrencyDto;
import com.soberk.xpends.currency.dto.mapper.CurrencyResponseMapper;
import com.soberk.xpends.currency.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
public class CurrencyServiceImpl implements CurrencyService{
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private CurrencyResponseMapper responseMapper;

    @Override
    public void create(CurrencyReqDto request) throws SQLException {
        currencyRepository.create(request.toEntity());
    }

    @Override
    public void update(Long id, CurrencyReqDto request) throws SQLException {
        Currency entity = request.toEntity();
        entity.setId(id);
        currencyRepository.update(entity);
    }

    @Override
    public void remove(Long id) throws SQLException {
        currencyRepository.delete(id);
    }

    @Override
    public CurrencyDto getById(Long id) throws SQLException {
        Currency entity = currencyRepository.get(id);
        if(Objects.isNull(entity)) throw new EntityNotFoundException();
        return responseMapper.toDto(entity);
    }

    @Override
    public List<CurrencyDto> getAll() throws SQLException {
        List<Currency> list = currencyRepository.getAll();
        return list.stream().map(entity -> responseMapper.toDto(entity)).toList();
    }
}
