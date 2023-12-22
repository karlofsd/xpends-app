package com.soberk.xpends.transaction_type.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@EqualsAndHashCode
@ToString
@Getter @Setter
public class TransactionTypeDto {
    private Long id;
    private String name;
    private String description;
    private boolean deleted;
}
