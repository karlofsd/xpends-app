package com.soberk.xpends.category.repository;

import com.soberk.xpends.category.domain.Category;
import com.soberk.xpends.core.interfaces.CRUDRepository;

import java.util.List;
import java.util.Map;

public interface CategoryRepository extends CRUDRepository<Category, Long> {
    List<Category> getByParams(Map<String,Object> params);
}
