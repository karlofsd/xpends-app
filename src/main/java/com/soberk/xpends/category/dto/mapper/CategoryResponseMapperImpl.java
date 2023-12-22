package com.soberk.xpends.category.dto.mapper;

import com.soberk.xpends.category.domain.Category;
import com.soberk.xpends.category.dto.response.CategoryDto;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CategoryResponseMapperImpl implements CategoryResponseMapper {
    @Override
    public CategoryDto toCategoryDto(Category category) {
        CategoryDto dto= new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setDeleted(category.isDeleted());
        return dto;
    }
}
