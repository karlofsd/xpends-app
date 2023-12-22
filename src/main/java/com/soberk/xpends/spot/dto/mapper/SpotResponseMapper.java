package com.soberk.xpends.spot.dto.mapper;

import com.soberk.xpends.spot.domain.Spot;
import com.soberk.xpends.spot.dto.response.SpotDto;

public interface SpotResponseMapper {
    SpotDto toDto(Spot spot);
}
                    