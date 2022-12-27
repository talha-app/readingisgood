package com.readingisgood.controller;

import com.readingisgood.dto.OrderDetailDTO;
import com.readingisgood.dto.request.CreateOrderRequestDTO;
import com.readingisgood.dto.request.ListCustomerOrdersByDateRequestDTO;
import com.readingisgood.dto.request.OrderDetailRequestDTO;
import com.readingisgood.entity.Customer;
import com.readingisgood.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
@Api(value = "Order Api")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService)
    {
        this.orderService = orderService;
    }

    @ApiOperation(value = "Create New Order", authorizations = {@Authorization(value = "jwtToken")})
    @PostMapping(value = "/create")
    public ResponseEntity<?> createOrder(@RequestBody  @Valid CreateOrderRequestDTO requestDTO) throws Exception
    {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderService.createOrder(requestDTO, customer);
    }

    @ApiOperation(value = "Get Order Detail", authorizations = {@Authorization(value = "jwtToken")})
    @PostMapping(value = "/detail")
    public ResponseEntity<OrderDetailDTO> getOrderDetail(@RequestBody OrderDetailRequestDTO requestDTO)
    {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderService.getOrderDetail(requestDTO, customer);
    }

    @ApiOperation(value = "List Orders By Date", authorizations = {@Authorization(value = "jwtToken")})
    @PostMapping(value = "/listbydate")
    public ResponseEntity<?> listCustomerOrdersByDate(@RequestBody ListCustomerOrdersByDateRequestDTO requestDTO)
    {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderService.listCustomerOrdersByDate(customer, requestDTO);
    }
}
