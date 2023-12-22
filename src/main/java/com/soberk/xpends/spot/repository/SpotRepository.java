package com.soberk.xpends.spot.repository;

import com.soberk.xpends.core.interfaces.CRUDRepository;
import com.soberk.xpends.spot.domain.Spot;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface SpotRepository extends CRUDRepository<Spot, Long> {
    List<Spot> getByParams(Map<String, Object> params) throws SQLException;
}
