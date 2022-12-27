package com.readingisgood.controller;

import com.readingisgood.dto.request.CreateNewBookRequestDTO;
import com.readingisgood.dto.request.UpdateStockRequestDTO;
import com.readingisgood.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@Api(value = "Book Api")
public class BookController {

    private final StockService stockService;

    public BookController(StockService stockService)
    {
        this.stockService = stockService;
    }

    @ApiOperation(value = "Update Stock", authorizations = { @Authorization(value="jwtToken") })
    @PostMapping(value = "/update")
    public ResponseEntity<?> updateStock(@RequestBody UpdateStockRequestDTO requestDTO)
    {
        return stockService.updateStock(requestDTO);
    }

    @ApiOperation(value = "Create New Book", authorizations = { @Authorization(value="jwtToken") })
    @PostMapping(value = "/create")
    public ResponseEntity<?> createNewBook(@RequestBody CreateNewBookRequestDTO requestDTO)
    {
        return stockService.createNewBook(requestDTO);
    }
}
