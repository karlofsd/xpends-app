package com.soberk.xpends.spot.dto.request;

import com.soberk.xpends.core.interfaces.RequestMapper;
import com.soberk.xpends.spot.domain.Spot;
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
public class SpotPatchReqDto implements RequestMapper<Spot> {
    private double amount;

    @Override
    public Spot toEntity() {
        Spot spot = new Spot();
        spot.setAmount(amount);
        return spot;
    }
}
