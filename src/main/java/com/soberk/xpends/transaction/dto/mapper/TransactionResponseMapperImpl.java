package com.soberk.xpends.transaction.dto.mapper;

import com.soberk.xpends.category.dto.mapper.CategoryResponseMapper;
import com.soberk.xpends.transaction.domain.Transaction;
import com.soberk.xpends.transaction.dto.response.TransactionDto;
import com.soberk.xpends.transaction.dto.response.TransactionWithCategoryDto;
import com.soberk.xpends.transaction_type.dto.mapper.TransactionTypeResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionResponseMapperImpl implements TransactionResponseMapper {
    @Autowired
    private TransactionTypeResponseMapper transactionTypeResponseMapper;
    @Autowired
    private CategoryResponseMapper categoryResponseMapper;

    @Override
    public TransactionDto toDto(Transaction transaction) {
        TransactionDto dto = new TransactionDto();
        dto.setId(transaction.getId());
        dto.setAmount(transaction.getAmount());
        dto.setDescription(transaction.getDescription());
        dto.setDateTime(transaction.getDateTime());
        dto.setType(transactionTypeResponseMapper.toDto(transaction.getType()));
        return dto;
    }

    @Override
    public TransactionWithCategoryDto toDtoWithCategory(Transaction transaction) {
        TransactionWithCategoryDto dto = new TransactionWithCategoryDto();
        dto.setId(transaction.getId());
        dto.setAmount(transaction.getAmount());
        dto.setDescription(transaction.getDescription());
        dto.setDateTime(transaction.getDateTime());
        dto.setType(transactionTypeResponseMapper.toDto(transaction.getType()));
        dto.setCategory(categoryResponseMapper.toCategoryDto(transaction.getCategory()));
        return dto;
    }
}
