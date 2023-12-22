package com.soberk.xpends.account.repository;

import com.soberk.xpends.account.domain.Account;
import com.soberk.xpends.core.interfaces.CRUDRepository;

import java.util.UUID;

public interface AccountRepository extends CRUDRepository<Account, UUID> {
}