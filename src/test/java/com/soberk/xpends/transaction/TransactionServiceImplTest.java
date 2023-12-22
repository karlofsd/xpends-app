package com.soberk.xpends.transaction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.soberk.xpends.core.exceptions.EntityNotFoundException;
import com.soberk.xpends.spot.service.SpotService;
import com.soberk.xpends.transaction.domain.Transaction;
import com.soberk.xpends.transaction.dto.mapper.TransactionResponseMapper;
import com.soberk.xpends.transaction.dto.request.TransactionReqDto;
import com.soberk.xpends.transaction.dto.response.TransactionDto;
import com.soberk.xpends.transaction.dto.response.TransactionWithCategoryDto;
import com.soberk.xpends.transaction.repository.TransactionRepository;
import com.soberk.xpends.transaction.service.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private SpotService spotService;

    @Mock
    private TransactionResponseMapper responseMapper;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void testCreate() throws SQLException {
//        // Arrange
//        TransactionReqDto request = new TransactionReqDto();
//        when(transactionRepository.create(any())).thenReturn(any());
//
//        // Act
//        transactionService.create(request);
//
//        // Assert
//        verify(transactionRepository, times(1)).create(any());
//    }

    @Test
    void testDelete() throws SQLException {
        // Arrange
        UUID transactionId = UUID.randomUUID();

        // Act
        transactionService.delete(transactionId);

        // Assert
        verify(transactionRepository, times(1)).delete(transactionId);
    }

    @Test
    void testGet() throws SQLException, EntityNotFoundException {
        // Arrange
        UUID transactionId = UUID.randomUUID();
        Transaction transaction = new Transaction();
        when(transactionRepository.get(any())).thenReturn(transaction);
        when(responseMapper.toDtoWithCategory(any())).thenReturn(new TransactionWithCategoryDto());

        // Act
        TransactionWithCategoryDto result = transactionService.get(transactionId);

        // Assert
        assertNotNull(result);
        verify(transactionRepository, times(1)).get(transactionId);
        verify(responseMapper, times(1)).toDtoWithCategory(any());
    }

    @Test
    void testGetAll() throws SQLException {
        // Arrange
        List<Transaction> transactionList = new ArrayList<>();
        when(transactionRepository.getAll()).thenReturn(transactionList);
        when(responseMapper.toDto(any())).thenReturn(new TransactionDto());

        // Act
        List<TransactionDto> resultList = transactionService.getAll(Map.of());

        // Assert
        assertNotNull(resultList);
        assertEquals(transactionList.size(), resultList.size());
        verify(transactionRepository, times(1)).getAll();
        verify(responseMapper, times(transactionList.size())).toDto(any());
    }

    @Test
    void testGetByDate() throws SQLException {
        // Arrange
        LocalDateTime minDate = LocalDateTime.now().minusDays(1);
        LocalDateTime maxDate = LocalDateTime.now();
        List<Transaction> transactionList = new ArrayList<>();
        when(transactionRepository.getBetween(minDate, maxDate)).thenReturn(transactionList);
        when(responseMapper.toDto(any())).thenReturn(new TransactionDto());

        // Act
        List<TransactionDto> resultList = transactionService.getByDate(minDate, maxDate);

        // Assert
        assertNotNull(resultList);
        verify(transactionRepository, times(1)).getBetween(minDate, maxDate);
        verify(responseMapper, times(transactionList.size())).toDto(any());
    }
}

