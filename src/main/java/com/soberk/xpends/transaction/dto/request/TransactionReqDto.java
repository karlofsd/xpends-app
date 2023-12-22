package com.soberk.xpends.transaction.dto.request;

import com.soberk.xpends.core.interfaces.RequestMapper;
import com.soberk.xpends.transaction.domain.Transaction;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Scope("prototype")
@ToString
@EqualsAndHashCode
@Getter @Setter
public class TransactionReqDto implements RequestMapper<Transaction> {
    private double amount;
    private String description;
    private LocalDateTime dateTime;
    private Long transactionTypeId;
    private Long spotId;
    private Long categoryId;

    @Override
    public Transaction toEntity() {
        return new Transaction(amount, description, dateTime, transactionTypeId, spotId, categoryId);
    }
}
