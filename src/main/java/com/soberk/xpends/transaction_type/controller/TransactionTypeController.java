package com.soberk.xpends.transaction_type.controller;

import com.soberk.xpends.core.models.ApiResponse;
import com.soberk.xpends.core.models.ApiResponseData;
import com.soberk.xpends.transaction_type.dto.request.TransactionTypeReqDto;
import com.soberk.xpends.transaction_type.dto.response.TransactionTypeDto;
import com.soberk.xpends.transaction_type.service.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api/transaction_type")
public class TransactionTypeController {
    @Autowired
    private TransactionTypeService transactionTypeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(@RequestBody TransactionTypeReqDto body) throws SQLException {
        transactionTypeService.create(body);
        return new ApiResponse(HttpStatus.CREATED.value(), "Se creó un tipo exitosamente");
    }

    @PatchMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse update(@PathVariable("id") Long id, @RequestBody TransactionTypeReqDto body) throws SQLException {
        transactionTypeService.update(id, body);
        return new ApiResponse(HttpStatus.OK.value(),"Se actualizó un tipo");
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse delete(@PathVariable("id") Long id) throws SQLException {
        transactionTypeService.remove(id);
        return new ApiResponse(HttpStatus.OK.value(),"Transacción eliminada con éxito");
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponseData<TransactionTypeDto> getById(@PathVariable("id") Long id) throws SQLException {
        TransactionTypeDto dto = transactionTypeService.getById(id);
        ApiResponseData<TransactionTypeDto> response = new ApiResponseData<>();
        response.setCode(HttpStatus.FOUND.value());
        response.setMessage("Tipo obtenido");
        response.setData(dto);
        return response;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponseData<List<TransactionTypeDto>> getById() throws SQLException {
        List<TransactionTypeDto> dtoList = transactionTypeService.getAll();
        ApiResponseData<List<TransactionTypeDto>> response = new ApiResponseData<>();
        response.setCode(HttpStatus.FOUND.value());
        response.setMessage("Tipos obtenidos: "+dtoList.size());
        response.setData(dtoList);
        return response;
    }
}
