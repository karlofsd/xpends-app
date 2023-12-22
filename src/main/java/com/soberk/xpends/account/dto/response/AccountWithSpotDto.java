package com.soberk.xpends.account.dto.response;

import com.soberk.xpends.spot.dto.response.SpotDto;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
@Getter @Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class AccountWithSpotDto extends AccountDto {
    private List<SpotDto> spot;
}
