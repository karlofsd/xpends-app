package com.soberk.xpends.currency.controller;

import com.soberk.xpends.core.models.ApiResponse;
import com.soberk.xpends.core.models.ApiResponseData;
import com.soberk.xpends.currency.dto.request.CurrencyReqDto;
import com.soberk.xpends.currency.dto.response.CurrencyDto;
import com.soberk.xpends.currency.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api/currency")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(@RequestBody CurrencyReqDto body) throws SQLException {
        currencyService.create(body);
        ApiResponse response = new ApiResponse();
        response.setCode(HttpStatus.CREATED.value());
        response.setMessage("Se creó una moneda exitosamente");
        return response;
    }

    @PatchMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse update(@PathVariable("id") Long id, @RequestBody CurrencyReqDto body) throws SQLException {
        currencyService.update(id, body);
        ApiResponse response = new ApiResponse();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Se actualizó una moneda");
        return response;
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse delete(@PathVariable("id") Long id) throws SQLException {
        currencyService.remove(id);
        ApiResponse response = new ApiResponse();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Moneda eliminada con éxito");
        return response;
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponseData<CurrencyDto> getById(@PathVariable("id") Long id) throws SQLException {
        CurrencyDto dto = currencyService.getById(id);
        ApiResponseData<CurrencyDto> response = new ApiResponseData<>();
        response.setCode(HttpStatus.FOUND.value());
        response.setMessage("Moneda obtenida");
        response.setData(dto);
        return response;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponseData<List<CurrencyDto>> getAll() throws SQLException {
        List<CurrencyDto> dtoList = currencyService.getAll();
        ApiResponseData<List<CurrencyDto>> response = new ApiResponseData<>();
        response.setCode(HttpStatus.FOUND.value());
        response.setMessage("Monedas obtenidas");
        response.setData(dtoList);
        return response;
    }
}
