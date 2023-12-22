package com.soberk.xpends.transaction.controller;

import com.soberk.xpends.core.models.ApiResponse;
import com.soberk.xpends.core.models.ApiResponseData;
import com.soberk.xpends.transaction.dto.request.TransactionReqDto;
import com.soberk.xpends.transaction.dto.response.TransactionDto;
import com.soberk.xpends.transaction.dto.response.TransactionWithCategoryDto;
import com.soberk.xpends.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(@RequestBody TransactionReqDto body) throws SQLException {
        transactionService.create(body);
        return new ApiResponse(HttpStatus.CREATED.value(), "Se creó una transacción exitosamente");
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponseData<TransactionWithCategoryDto> getById(@PathVariable("id") String id) throws SQLException {
        TransactionWithCategoryDto dto = transactionService.get(UUID.fromString(id));
        ApiResponseData<TransactionWithCategoryDto> response = new ApiResponseData<>();
        response.setCode(HttpStatus.FOUND.value());
        response.setMessage("Transacción obtenida");
        response.setData(dto);
        return response;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponseData<List<TransactionDto>> getAll(@RequestParam Map<String,Object> params) throws SQLException {
//        Map<String,Object> params = Map.of("transaction_type",type,"spot_id",spotId,"category_id",categoryId);
        params.entrySet().forEach(param -> {
            if(Objects.isNull(param.getValue())) params.remove(param.getKey());
        });
        List<TransactionDto> dtoList = transactionService.getAll(params);
        ApiResponseData<List<TransactionDto>> response = new ApiResponseData<>();
        response.setCode(HttpStatus.FOUND.value());
        response.setMessage("Transacciones obtenidas");
        response.setData(dtoList);
        return response;
    }
}
