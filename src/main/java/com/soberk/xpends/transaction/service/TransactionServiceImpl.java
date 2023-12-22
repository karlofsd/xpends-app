package com.soberk.xpends.transaction.service;

import com.soberk.xpends.core.exceptions.EntityNotFoundException;
import com.soberk.xpends.core.interfaces.TransactionOperations;
import com.soberk.xpends.spot.domain.Spot;
import com.soberk.xpends.spot.repository.SpotRepository;
import com.soberk.xpends.spot.service.SpotService;
import com.soberk.xpends.transaction.dto.request.TransactionReqDto;
import com.soberk.xpends.transaction.dto.response.TransactionDto;
import com.soberk.xpends.transaction.domain.Transaction;
import com.soberk.xpends.transaction.dto.response.TransactionWithCategoryDto;
import com.soberk.xpends.transaction.dto.mapper.TransactionResponseMapper;
import com.soberk.xpends.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private SpotService spotService;
    @Autowired
    private TransactionResponseMapper responseMapper;

    @Override
    public void create(TransactionReqDto request) throws SQLException {
        System.out.println("-->Creando transacción...");
        transactionRepository.create(request.toEntity());
        TransactionOperations operation = request.getTransactionTypeId() == 1 ? TransactionOperations.DEPOSIT : TransactionOperations.DRAW;
        System.out.println("-->Actualizando spot...");
        spotService.update(request.getSpotId(),request.getAmount(),operation);
    }

    @Override
    public void delete(UUID id) throws SQLException {
        transactionRepository.delete(id);
    }

    @Override
    public TransactionWithCategoryDto get(UUID id) throws SQLException {
        System.out.println("-->Obteniendo transacción...");
        Transaction transaction = transactionRepository.get(id);
        if(Objects.isNull(transaction)) throw new EntityNotFoundException();
        System.out.println("-->Mapeando transacción...");
        return responseMapper.toDtoWithCategory(transaction);
    }

    @Override
    public List<TransactionDto> getAll(Map<String, Object> params) throws SQLException {
        System.out.println("-->Obteniendo transacción...");
        List<Transaction> transactions = params.isEmpty() ? transactionRepository.getAll() : transactionRepository.getByParams(params);
        System.out.println("-->Mapeando transacciones...");
        return transactions.stream().map(transaction -> responseMapper.toDto(transaction)).toList();
    }

    @Override
    public List<TransactionDto> getByDate(LocalDateTime minDate, LocalDateTime maxDate) throws SQLException {
        List<Transaction> transactions = transactionRepository.getBetween(minDate,maxDate);
        return transactions.stream().map(transaction -> responseMapper.toDto(transaction)).toList();
    }
}
