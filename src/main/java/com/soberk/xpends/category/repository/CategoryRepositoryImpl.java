package com.soberk.xpends.category.repository;

import com.soberk.xpends.category.domain.Category;
import com.soberk.xpends.core.utilities.EntityUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

@Repository
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {
    private static final String DELETE = "DELETE Category WHERE id=:id";
    private static final String SELECT_ALL= "FROM Category";
    private static final String NATIVE_SELECT_ALL = "SELECT * FROM categories";
    private static final String NATIVE_UPDATE = "UPDATE categories SET";
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Category create(Category entity) throws SQLException {
        return entityManager.merge(entity);
    }

    @Override
    public void update(Category entity) throws SQLException {
        Category response = entityManager.find(Category.class,entity.getId());
        if(response != null){
            EntityUtils.copyProperties(entity,response,Category.class);
            entityManager.merge(response);
        } else throw new SQLException();
    }

    @Override
    public void delete(Long id) throws SQLException {
        entityManager.createQuery("UPDATE Category SET deleted=true WHERE id=:id").setParameter("id",id).executeUpdate();
    }

    @Override
    public Category get(Long id) throws SQLException {
        return entityManager.find(Category.class,id);
    }

    @Override
    public List<Category> getAll() throws SQLException {
        return entityManager.createQuery(SELECT_ALL,Category.class).getResultList();
    }

    @Override
    public List<Category> getByParams(Map<String, Object> params) {
        List<String> fieldList = new ArrayList<>();
        params.entrySet().forEach(param -> {
            fieldList.add(String.format("%s='%s'", param.getKey(), param.getValue()));
        });
        String query = String.format("%s WHERE %s", NATIVE_SELECT_ALL, String.join(" AND ", fieldList));
        List<Category> categories = entityManager.createNativeQuery(query,Category.class).getResultList();
        return categories;
    }
}
