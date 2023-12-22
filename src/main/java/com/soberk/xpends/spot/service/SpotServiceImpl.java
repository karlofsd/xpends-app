package com.soberk.xpends.spot.service;

import com.soberk.xpends.core.exceptions.EntityNotFoundException;
import com.soberk.xpends.core.interfaces.TransactionOperations;
import com.soberk.xpends.spot.dto.request.SpotReqDto;
import com.soberk.xpends.spot.dto.response.SpotDto;
import com.soberk.xpends.spot.dto.mapper.SpotResponseMapper;
import com.soberk.xpends.spot.domain.Spot;
import com.soberk.xpends.spot.repository.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class SpotServiceImpl implements SpotService {
    @Autowired
    private SpotRepository spotRepository;

    @Autowired
    private SpotResponseMapper responseMapper;

    @Override
    public void create(SpotReqDto request) throws SQLException {
        spotRepository.create(request.toEntity());
    }

    @Override
    public void update(Long id, double amount, TransactionOperations operation) throws SQLException {
        Spot entity = spotRepository.get(id);
        if(entity == null) throw new SQLException();
        double newAmount = operation == TransactionOperations.DEPOSIT ? entity.getAmount()+amount : entity.getAmount()-amount;
        entity.setAmount(newAmount);
        spotRepository.update(entity);
    }

    @Override
    public void remove(Long id) throws SQLException {
        spotRepository.delete(id);
    }

    @Override
    public SpotDto get(Long id) throws SQLException {
        Spot spot = spotRepository.get(id);
        if(Objects.isNull(spot)) throw new EntityNotFoundException();
        return responseMapper.toDto(spot);
    }

    @Override
    public List<SpotDto> getAll(Map<String, Object> params) throws SQLException {
        List<Spot> list = params.isEmpty() ? spotRepository.getAll() : spotRepository.getByParams(params);
        return list.stream().map(spot -> responseMapper.toDto(spot)).toList();
    }
}
