package com.soberk.xpends.transaction_type.repository;

import com.soberk.xpends.category.domain.Category;
import com.soberk.xpends.core.utilities.EntityUtils;
import com.soberk.xpends.transaction_type.domain.TransactionType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class TransactionTypeRepositoryImpl implements TransactionTypeRepository{
    private static final String DELETE = "DELETE TransactionType WHERE id=:id";
    private static final String SELECT_ALL= "FROM TransactionType";
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public TransactionType create(TransactionType entity) throws SQLException {
        return entityManager.merge(entity);
    }

    @Override
    public void update(TransactionType entity) throws SQLException {
        TransactionType response = entityManager.find(TransactionType.class,entity.getId());
        if(response != null){
            EntityUtils.copyProperties(entity,response,TransactionType.class);
            entityManager.merge(response);
        } else throw new SQLException();
    }

    @Override
    public void delete(Long id) throws SQLException {
        entityManager.createQuery("UPDATE TransactionType SET deleted=true WHERE id=:id").setParameter("id",id).executeUpdate();
    }

    @Override
    public TransactionType get(Long id) throws SQLException {
        return entityManager.find(TransactionType.class,id);
    }

    @Override
    public List<TransactionType> getAll() throws SQLException {
        return entityManager.createQuery(SELECT_ALL,TransactionType.class).getResultList();
    }
}
