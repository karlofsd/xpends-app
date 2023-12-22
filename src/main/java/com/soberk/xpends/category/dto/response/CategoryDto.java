package com.soberk.xpends.category.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@ToString
@EqualsAndHashCode
@Getter @Setter
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private boolean deleted;
}
