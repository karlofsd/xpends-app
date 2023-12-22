package com.soberk.xpends.currency.dto.request;

import com.soberk.xpends.core.interfaces.RequestMapper;
import com.soberk.xpends.currency.domain.Currency;
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
public class CurrencyReqDto implements RequestMapper<Currency> {
    private String name;
    private String code;
    private String symbol;
    private double exchange;

    @Override
    public Currency toEntity() {
        return new Currency(name,code,symbol,exchange);
    }
}
