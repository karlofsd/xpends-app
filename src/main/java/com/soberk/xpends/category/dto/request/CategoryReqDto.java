package com.soberk.xpends.category.dto.request;

import com.soberk.xpends.category.domain.Category;
import com.soberk.xpends.core.interfaces.RequestMapper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@EqualsAndHashCode
@ToString
@Getter @Setter
public class CategoryReqDto implements RequestMapper<Category> {
    private String name;
    private String description;

    @Override
    public Category toEntity() {
        return new Category(name,description);
    }
}
