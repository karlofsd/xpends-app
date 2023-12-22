package com.soberk.xpends.currency.repository;

import com.soberk.xpends.category.domain.Category;
import com.soberk.xpends.core.utilities.EntityUtils;
import com.soberk.xpends.currency.domain.Currency;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class CurrencyRepositoryImpl implements CurrencyRepository{
    private static final String DELETE = "DELETE Currency WHERE id=:id";
    private static final String SELECT_ALL= "FROM Currency";
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Currency create(Currency entity) throws SQLException {
        return entityManager.merge(entity);
    }

    @Override
    public void update(Currency entity) throws SQLException {
        Currency response = entityManager.find(Currency.class,entity.getId());
        System.out.println(response);
        if(response != null){
            EntityUtils.copyProperties(entity,response,Currency.class);
            entityManager.merge(response);
        } else throw new SQLException();
    }

    @Override
    public void delete(Long id) throws SQLException {
        entityManager.createQuery("UPDATE Currency SET deleted=true WHERE id=:id").setParameter("id",id).executeUpdate();
    }

    @Override
    public Currency get(Long id) throws SQLException {
        return entityManager.find(Currency.class,id);
    }

    @Override
    public List<Currency> getAll() throws SQLException {
        return entityManager.createQuery(SELECT_ALL,Currency.class).getResultList();
    }
}
