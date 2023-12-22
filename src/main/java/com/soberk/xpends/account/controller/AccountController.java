package com.soberk.xpends.account.controller;

import com.soberk.xpends.account.dto.request.AccountReqDto;
import com.soberk.xpends.account.dto.request.AccountWithSpotReqDto;
import com.soberk.xpends.account.dto.response.AccountDto;
import com.soberk.xpends.account.dto.response.AccountWithSpotDto;
import com.soberk.xpends.account.service.AccountService;
import com.soberk.xpends.core.exceptions.EntityNotFoundException;
import com.soberk.xpends.core.models.ApiResponse;
import com.soberk.xpends.core.models.ApiResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(@RequestBody AccountWithSpotReqDto body) throws SQLException {
        System.out.println(body.toString());
        accountService.create(body);
        return new ApiResponse(HttpStatus.CREATED.value(),"Cuenta creada con éxito");
    }

    @PatchMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse update(@PathVariable("id") UUID id, @RequestBody AccountReqDto body) throws SQLException{
        accountService.update(id, body);
        return new ApiResponse(HttpStatus.OK.value(),"");
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse remove(@PathVariable("id") UUID id) throws SQLException{
        accountService.delete(id);
        return new ApiResponse(HttpStatus.OK.value(),"");
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponseData<AccountWithSpotDto> getById(@PathVariable("id") UUID id) throws SQLException, EntityNotFoundException {
        AccountWithSpotDto dto = accountService.get(id);
        ApiResponseData<AccountWithSpotDto> response = new ApiResponseData<>();
        response.setCode(HttpStatus.FOUND.value());
        response.setMessage("");
        response.setData(dto);
        return response;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponseData<List<AccountDto>> getAll() throws SQLException {
        List<AccountDto> dtoList = accountService.getAll();
        ApiResponseData<List<AccountDto>> response = new ApiResponseData<>();
        response.setCode(HttpStatus.FOUND.value());
        response.setMessage("");
        response.setData(dtoList);
        return response;
    }
}
