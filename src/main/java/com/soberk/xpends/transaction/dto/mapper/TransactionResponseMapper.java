package com.soberk.xpends.transaction.dto.mapper;

import com.soberk.xpends.transaction.domain.Transaction;
import com.soberk.xpends.transaction.dto.response.TransactionDto;
import com.soberk.xpends.transaction.dto.response.TransactionWithCategoryDto;

public interface TransactionResponseMapper {
    TransactionDto toDto(Transaction transaction);
    TransactionWithCategoryDto toDtoWithCategory(Transaction transaction);
}
