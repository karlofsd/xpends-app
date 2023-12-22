package com.soberk.xpends.account.dto.request;

import com.soberk.xpends.account.domain.Account;
import com.soberk.xpends.core.interfaces.RequestMapper;
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
public class AccountReqDto implements RequestMapper<Account> {
    private String name;
    private String description;

    @Override
    public Account toEntity() {
        return new Account(name,description);
    }
}
