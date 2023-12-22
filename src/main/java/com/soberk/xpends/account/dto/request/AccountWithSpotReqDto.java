package com.soberk.xpends.account.dto.request;

import com.soberk.xpends.account.domain.Account;
import com.soberk.xpends.core.interfaces.RequestMapper;
import com.soberk.xpends.spot.dto.request.SpotReqDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
@Setter
public class AccountWithSpotReqDto extends AccountReqDto{
    private SpotReqDto spot;
}
