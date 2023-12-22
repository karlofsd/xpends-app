package com.soberk.xpends.account.repository;

import com.soberk.xpends.account.domain.Account;

import com.soberk.xpends.category.domain.Category;
import com.soberk.xpends.core.exceptions.EntityNotFoundException;
import com.soberk.xpends.core.utilities.EntityUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
@Transactional
public class AccountRepositoryImpl implements AccountRepository {
    private static final String DELETE = "DELETE Account WHERE id=:id";
    private static final String SELECT_ALL= "FROM Account";

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Account create(Account entity) throws SQLException {
        Account acc = entityManager.merge(entity);
        if (acc == null) throw new SQLException("Error al crear cuenta");
        return acc;
    }

    @Override
    public void update(Account entity) throws SQLException {
        Account response = entityManager.find(Account.class,entity.getId());
        if(response != null){
            EntityUtils.copyProperties(entity,response,Account.class);
            Account acc = entityManager.merge(response);
        if(acc == null) throw new SQLException("Error al actualizar cuenta");
        } else throw new SQLException();
    }

    @Override
    public void delete(UUID id) throws SQLException {
        int isSuccessfully = entityManager.createQuery(DELETE).setParameter("id",id).executeUpdate();
        if(isSuccessfully == 0) throw new SQLException("Error al eliminar la cuenta");
    }

    @Override
    public Account get(UUID id) throws SQLException, EntityNotFoundException {
        Account entity = entityManager.find(Account.class, id);
        if(Objects.isNull(entity)) throw new EntityNotFoundException("Account not found");
        return entity;
    }

    @Override
    public List<Account> getAll() {
        return  entityManager.createQuery(SELECT_ALL,Account.class).getResultList();
    }
}
