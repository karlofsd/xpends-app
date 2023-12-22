package com.soberk.xpends.category.service;

import com.soberk.xpends.category.dto.request.CategoryReqDto;
import com.soberk.xpends.category.dto.response.CategoryDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CategoryService {
    void create(CategoryReqDto dto) throws SQLException;
    void update(Long id, CategoryReqDto dto) throws SQLException;
    void remove(Long id) throws SQLException;
    CategoryDto get(Long id) throws SQLException;
    List<CategoryDto> getAll(Map<String, Object> params) throws SQLException;
}
