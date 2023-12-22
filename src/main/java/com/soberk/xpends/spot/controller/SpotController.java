package com.soberk.xpends.spot.controller;

import com.soberk.xpends.core.models.ApiResponse;
import com.soberk.xpends.core.models.ApiResponseData;
import com.soberk.xpends.spot.dto.request.SpotPatchReqDto;
import com.soberk.xpends.spot.dto.request.SpotReqDto;
import com.soberk.xpends.spot.dto.response.SpotDto;
import com.soberk.xpends.spot.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping("api/spot")
public class SpotController {
    @Autowired
    private SpotService spotService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(@RequestBody SpotReqDto body) throws SQLException {
        spotService.create(body);
        return new ApiResponse(HttpStatus.CREATED.value(), "Se creó un spot exitosamente");
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse delete(@PathVariable("id") Long id) throws SQLException {
        spotService.remove(id);
        return new ApiResponse(HttpStatus.OK.value(), "Spot eliminado con éxito");
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponseData<SpotDto> getById(@PathVariable("id") Long id) throws SQLException {
        SpotDto dto = spotService.get(id);
        ApiResponseData<SpotDto> response = new ApiResponseData<>();
        response.setCode(HttpStatus.FOUND.value());
        response.setMessage("Spot obtenido");
        response.setData(dto);
        return response;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponseData<List<SpotDto>> getAll(@RequestParam Map<String,Object> params) throws SQLException {
        params.entrySet().forEach(param ->{
            if (Objects.isNull(param.getValue())) params.remove(param.getKey());
        });
        List<SpotDto> dtoList = spotService.getAll(params);
        ApiResponseData<List<SpotDto>> response = new ApiResponseData<>();
        response.setCode(HttpStatus.FOUND.value());
        response.setMessage("Spots obtenidos");
        response.setData(dtoList);
        return response;
    }
}
