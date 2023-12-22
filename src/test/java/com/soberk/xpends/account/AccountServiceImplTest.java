package com.soberk.xpends.account;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.soberk.xpends.account.domain.Account;
import com.soberk.xpends.account.dto.mapper.AccountResponseMapper;
import com.soberk.xpends.account.dto.request.AccountWithSpotReqDto;
import com.soberk.xpends.account.dto.response.AccountDto;
import com.soberk.xpends.account.dto.response.AccountWithSpotDto;
import com.soberk.xpends.account.repository.AccountRepository;
import com.soberk.xpends.account.service.AccountServiceImpl;
import com.soberk.xpends.core.exceptions.EntityNotFoundException;
import com.soberk.xpends.spot.domain.Spot;
import com.soberk.xpends.spot.dto.request.SpotReqDto;
import com.soberk.xpends.spot.repository.SpotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private SpotRepository spotRepository;

    @Mock
    private AccountResponseMapper responseMapper;

    @Mock
    private AccountWithSpotReqDto accountWithSpotReqDto;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDelete() throws SQLException {
        // Arrange
        UUID accountId = UUID.randomUUID();

        // Act
        accountService.delete(accountId);

        // Assert
        verify(accountRepository, times(1)).delete(accountId);
    }

    @Test
    void testGet() throws SQLException, EntityNotFoundException {
        // Arrange
        UUID accountId = UUID.randomUUID();
        Account acc = new Account();
        when(accountRepository.get(any())).thenReturn(acc);
        when(responseMapper.toDtoWithSpot(any())).thenReturn(new AccountWithSpotDto());

        // Act
        AccountWithSpotDto result = accountService.get(accountId);

        // Assert
        assertNotNull(result);
        verify(accountRepository, times(1)).get(accountId);
        verify(responseMapper, times(1)).toDtoWithSpot(any());
    }

    @Test
    void testGetAll() throws SQLException {
        // Arrange
        List<Account> accountList = new ArrayList<>();
        when(accountRepository.getAll()).thenReturn(accountList);
        when(responseMapper.toDto(any())).thenReturn(new AccountDto());

        // Act
        List<AccountDto> resultList = accountService.getAll();

        // Assert
        assertNotNull(resultList);
        assertEquals(accountList.size(), resultList.size());
        verify(accountRepository, times(1)).getAll();
        verify(responseMapper, times(accountList.size())).toDto(any());
    }
}

