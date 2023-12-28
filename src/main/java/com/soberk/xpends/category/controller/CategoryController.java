package com.soberk.xpends.category.controller;

import com.soberk.xpends.category.dto.request.CategoryReqDto;
import com.soberk.xpends.category.dto.response.CategoryDto;
import com.soberk.xpends.category.service.CategoryService;
import com.soberk.xpends.core.models.ApiResponse;
import com.soberk.xpends.core.models.ApiResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(@RequestBody CategoryReqDto body) throws SQLException {
        categoryService.create(body);
        return new ApiResponse(HttpStatus.CREATED.value(), "La categoría fue creada con éxito");
    }

    @PatchMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse update(@PathVariable("id") Long id, @RequestBody CategoryReqDto body) throws SQLException {
        categoryService.update(id,body);
        return new ApiResponse(HttpStatus.OK.value(), "La categoría se actualizó correctamente");
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse delete(@PathVariable("id") Long id) throws SQLException {
        categoryService.remove(id);
        return new ApiResponse(HttpStatus.OK.value(), "La categoría fue eliminada correctamente");
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseData<CategoryDto> getById(@PathVariable("id") Long id) throws SQLException {
        CategoryDto dto = categoryService.get(id);
        ApiResponseData<CategoryDto> response = new ApiResponseData<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Categoría obtenida");
        response.setData(dto);
        return response;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseData<List<CategoryDto>> getAll(@RequestParam Map<String,Object> params) throws SQLException {
        params.entrySet().forEach(param ->{
            if (Objects.isNull(param.getValue())) params.remove(param.getKey());
        });
        List<CategoryDto> dtoList = categoryService.getAll(params);
        ApiResponseData<List<CategoryDto>> response = new ApiResponseData<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Categorías obtenidas");
        response.setData(dtoList);
        return response;
    }
}
