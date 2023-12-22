package com.soberk.xpends.transaction_type.dto.mapper;

import com.soberk.xpends.transaction_type.domain.TransactionType;
import com.soberk.xpends.transaction_type.dto.response.TransactionTypeDto;

public interface TransactionTypeResponseMapper {
    TransactionTypeDto toDto(TransactionType transactionType);
}
