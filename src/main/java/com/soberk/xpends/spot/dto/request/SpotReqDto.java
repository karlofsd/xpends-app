package com.soberk.xpends.spot.dto.request;

import com.soberk.xpends.core.interfaces.RequestMapper;
import com.soberk.xpends.spot.domain.Spot;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope("prototype")
@EqualsAndHashCode
@ToString
@Getter @Setter
public class SpotReqDto implements RequestMapper<Spot> {
    private double amount;
    private Long currencyId;
    private UUID accountId;

    @Override
    public Spot toEntity() {
       return new Spot(amount,currencyId,accountId);
    }
}
