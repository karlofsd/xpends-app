package com.soberk.xpends.category.service;

import com.soberk.xpends.category.domain.Category;
import com.soberk.xpends.category.dto.mapper.CategoryResponseMapper;
import com.soberk.xpends.category.dto.request.CategoryReqDto;
import com.soberk.xpends.category.dto.response.CategoryDto;
import com.soberk.xpends.category.repository.CategoryRepository;
import com.soberk.xpends.core.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryResponseMapper responseMapper;

    @Override
    public void create(CategoryReqDto dto) throws SQLException {
        categoryRepository.create(dto.toEntity());
    }

    @Override
    public void update(Long id, CategoryReqDto dto) throws SQLException {
        Category category = dto.toEntity();
        category.setId(id);
        categoryRepository.update(category);
    }

    @Override
    public void remove(Long id) throws SQLException {
        categoryRepository.delete(id);
    }

    @Override
    public CategoryDto get(Long id) throws SQLException, EntityNotFoundException {
        Category category = categoryRepository.get(id);
        if(Objects.isNull(category)) throw new EntityNotFoundException();
        return responseMapper.toCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAll(Map<String, Object> params) throws SQLException {
        List<Category> categories = params.isEmpty() ? categoryRepository.getAll() : categoryRepository.getByParams(params);
        return categories.stream().map(category -> responseMapper.toCategoryDto(category)).toList();
    }
}
