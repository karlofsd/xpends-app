package com.soberk.xpends.category.dto.mapper;

import com.soberk.xpends.category.domain.Category;
import com.soberk.xpends.category.dto.response.CategoryDto;

public interface CategoryResponseMapper {
    CategoryDto toCategoryDto(Category category);
}
