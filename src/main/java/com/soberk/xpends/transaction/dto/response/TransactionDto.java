package com.soberk.xpends.transaction.dto.response;

import com.soberk.xpends.transaction_type.dto.response.TransactionTypeDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Scope("prototype")
@ToString
@EqualsAndHashCode
@Getter @Setter
public class TransactionDto {
    private UUID id;
    private double amount;
    private String description;
    private LocalDateTime dateTime;
    private TransactionTypeDto type;
}
