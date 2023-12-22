package com.soberk.xpends.account.dto.response;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope("prototype")
@ToString
@EqualsAndHashCode
@Getter @Setter
public class AccountDto {
    private UUID id;
    private String name;
    private String description;
}
