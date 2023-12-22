package com.soberk.xpends.spot.dto.response;

import com.soberk.xpends.currency.dto.response.CurrencyDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope("prototype")
@ToString
@EqualsAndHashCode
@Getter @Setter
public class SpotDto {
    private Long id;
    private double amount;
    private CurrencyDto currency;
}
