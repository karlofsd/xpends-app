package com.soberk.xpends.transaction_type.dto.mapper;

import com.soberk.xpends.transaction_type.domain.TransactionType;
import com.soberk.xpends.transaction_type.dto.response.TransactionTypeDto;
import org.springframework.stereotype.Component;

@Component
public class TransactionTypeResponseMapperImpl implements TransactionTypeResponseMapper{
    @Override
    public TransactionTypeDto toDto(TransactionType transactionType) {
        TransactionTypeDto dto = new TransactionTypeDto();
        dto.setId(transactionType.getId());
        dto.setName(transactionType.getName());
        dto.setDescription(transactionType.getDescription());
        dto.setDeleted(transactionType.isDeleted());
        return dto;
    }
}
