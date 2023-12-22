package com.soberk.xpends.transaction_type.service;

import com.soberk.xpends.core.exceptions.EntityNotFoundException;
import com.soberk.xpends.transaction_type.domain.TransactionType;
import com.soberk.xpends.transaction_type.dto.request.TransactionTypeReqDto;
import com.soberk.xpends.transaction_type.dto.response.TransactionTypeDto;
import com.soberk.xpends.transaction_type.dto.mapper.TransactionTypeResponseMapper;
import com.soberk.xpends.transaction_type.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionTypeServiceImpl implements TransactionTypeService{
    @Autowired
    private TransactionTypeRepository transactionTypeRepository;
    @Autowired
    private TransactionTypeResponseMapper responseMapper;

    @Override
    public void create(TransactionTypeReqDto request) throws SQLException {
        transactionTypeRepository.create(request.toEntity());
    }

    @Override
    public void update(Long id, TransactionTypeReqDto request) throws SQLException {
        TransactionType entity = request.toEntity();
        entity.setId(id);
        transactionTypeRepository.update(entity);
    }

    @Override
    public void remove(Long id) throws SQLException {
        transactionTypeRepository.delete(id);
    }

    @Override
    public TransactionTypeDto getById(Long id) throws SQLException {
        TransactionType entity = transactionTypeRepository.get(id);
        if(Objects.isNull(entity)) throw new EntityNotFoundException();
        return responseMapper.toDto(entity);
    }

    @Override
    public List<TransactionTypeDto> getAll() throws SQLException {
        List<TransactionType> list = transactionTypeRepository.getAll();
        return list.stream().map(entity -> responseMapper.toDto(entity)).toList();
    }
}
