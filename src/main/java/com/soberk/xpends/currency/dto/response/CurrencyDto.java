package com.soberk.xpends.currency.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Getter @Setter
@EqualsAndHashCode
@ToString
public class CurrencyDto {
    private Long id;
    private String name;
    private String code;
    private String symbol;
    private double exchange;
    private boolean deleted;
}
