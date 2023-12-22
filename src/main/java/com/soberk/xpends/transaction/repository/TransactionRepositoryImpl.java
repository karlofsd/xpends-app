package com.soberk.xpends.transaction.repository;

import com.soberk.xpends.category.domain.Category;
import com.soberk.xpends.core.utilities.EntityUtils;
import com.soberk.xpends.transaction.domain.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@Transactional
public class TransactionRepositoryImpl implements TransactionRepository {
    private static final String DELETE = "DELETE Transaction WHERE id=:id";
    private static final String SELECT_ALL= "FROM Transaction";
    private static final String NATIVE_SELECT_ALL = "SELECT * FROM transactions";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Transaction create(Transaction entity) throws SQLException {
        return entityManager.merge(entity);
    }

    @Override
    public void update(Transaction entity) throws SQLException {
        Transaction response = entityManager.find(Transaction.class,entity.getId());
        if(response != null){
            EntityUtils.copyProperties(entity,response,Transaction.class);
            entityManager.merge(response);
        } else throw new SQLException();
    }

    @Override
    public void delete(UUID id) throws SQLException {
        entityManager.createQuery(DELETE).setParameter("id",id).executeUpdate();
    }

    @Override
    public Transaction get(UUID id) throws SQLException {
        return entityManager.find(Transaction.class,id);
    }

    @Override
    public List<Transaction> getAll() throws SQLException {
        return entityManager.createQuery(SELECT_ALL,Transaction.class).getResultList();
    }

    @Override
    public List<Transaction> getByParams(Map<String,Object> params) throws SQLException{
        List<String> fieldList = new ArrayList<>();
        params.entrySet().forEach(param ->
            fieldList.add(String.format("%s='%s'", param.getKey(), param.getValue()))
        );
        String query = String.format("%s WHERE %s", NATIVE_SELECT_ALL, String.join(" AND ", fieldList));
        return entityManager.createNativeQuery(query,Transaction.class).getResultList();
    }

    @Override
    public List<Transaction> getBetween(LocalDateTime minDate, LocalDateTime maxDate) throws SQLException{
        String query = SELECT_ALL + " WHERE :minDate BETWEEN :maxDate";
        return entityManager.createQuery(query,Transaction.class).setParameter("minDate",minDate.toLocalDate()).setParameter("maxDate",maxDate.toLocalDate()).getResultList();
    }

}
