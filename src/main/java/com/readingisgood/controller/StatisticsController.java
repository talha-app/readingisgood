package com.readingisgood.controller;

import com.readingisgood.entity.Customer;
import com.readingisgood.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@Api(value = "Statistics Api")
public class StatisticsController {
    private final OrderService orderService;

    public StatisticsController(OrderService orderService)
    {
        this.orderService = orderService;
    }

    @ApiOperation(value = "Customer’s monthly order statistics", authorizations = {@Authorization(value = "jwtToken")})
    @PostMapping(value = "/monthly")
    public ResponseEntity<?> generateMonthlyStatistics()
    {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderService.getMonthlyStatistics(customer);
    }
}
