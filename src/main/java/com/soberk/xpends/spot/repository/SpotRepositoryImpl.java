package com.soberk.xpends.spot.repository;

import com.soberk.xpends.category.domain.Category;
import com.soberk.xpends.core.utilities.EntityUtils;
import com.soberk.xpends.spot.domain.Spot;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.*;

@Repository
@Transactional
public class SpotRepositoryImpl implements SpotRepository {
    private static final String CREATE = "INSERT INTO spot(name,description) VALUES(:name,:description)";
    private static final String UPDATE = "UPDATE Spot SET name=:name,description=:description WHERE id=:id";
    private static final String DELETE = "DELETE Spot WHERE id=:id";
    private static final String SELECT_ALL= "FROM Spot";
    private static final String SELECT_ONE= SELECT_ALL + " WHERE id=:id";

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate template;

    @Override
    public Spot create(Spot entity) throws SQLException {
        return entityManager.merge(entity);
    }

    @Override
    public void update(Spot entity) throws SQLException {
        Spot response = entityManager.find(Spot.class,entity.getId());
        if(response != null){
            EntityUtils.copyProperties(entity,response,Spot.class);
            entityManager.merge(response);
        } else throw new SQLException();
    }

    @Override
    public void delete(Long id) throws SQLException {
        entityManager.createQuery(DELETE).setParameter("id",id).executeUpdate();
    }

    @Override
    public Spot get(Long id) throws SQLException {
        return entityManager.find(Spot.class, id);
    }

    @Override
    public List<Spot> getAll() throws SQLException {
        return entityManager.createQuery(SELECT_ALL,Spot.class).getResultList();
    }

    @Override
    public List<Spot> getByParams(Map<String, Object> params) throws SQLException {
        List<String> fieldList = new ArrayList<>();
        params.entrySet().forEach(param -> {
                fieldList.add(String.format("%s='%s'", param.getKey(), param.getValue()));
        });
        String query = String.format("SELECT * %s WHERE %s", SELECT_ALL, String.join(" AND ", fieldList));
        List<Spot> spots = entityManager.createNativeQuery(query, Spot.class).getResultList();
        return spots;
    }
}
