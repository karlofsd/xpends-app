package com.soberk.xpends.transaction.dto.response;

import com.soberk.xpends.category.dto.response.CategoryDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@ToString
@EqualsAndHashCode(callSuper = true)
@Getter @Setter
public class TransactionWithCategoryDto extends TransactionDto {
    private CategoryDto category;
}
