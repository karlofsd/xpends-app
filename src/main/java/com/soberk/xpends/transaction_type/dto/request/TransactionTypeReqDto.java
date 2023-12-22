package com.soberk.xpends.transaction_type.dto.request;

import com.soberk.xpends.core.interfaces.RequestMapper;
import com.soberk.xpends.transaction_type.domain.TransactionType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@ToString
@EqualsAndHashCode
@Getter @Setter
public class TransactionTypeReqDto implements RequestMapper<TransactionType> {
    private String name;
    private String description;

    @Override
    public TransactionType toEntity() {
        return new TransactionType(name, description);
    }
}
