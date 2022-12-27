package com.readingisgood.controller;

import com.readingisgood.dto.OrderDetailDTO;
import com.readingisgood.dto.request.CreateCustomerRequestDTO;
import com.readingisgood.dto.request.ListCustomerOrdersRequestDTO;
import com.readingisgood.dto.request.OrderDetailRequestDTO;
import com.readingisgood.entity.Customer;
import com.readingisgood.service.CustomerService;
import com.readingisgood.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin
@Api(value = "Customer Api")
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;

    public CustomerController(CustomerService customerService, OrderService orderService)
    {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @ApiOperation(value = "Create Customer")
    @PostMapping(value = "/create")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody CreateCustomerRequestDTO createCustomerRequestDTO) throws Exception
    {
        return customerService.createCustomer(createCustomerRequestDTO);
    }

    @ApiOperation(value = "List All Orders", authorizations = {@Authorization(value = "jwtToken")})
    @PostMapping(value = "/listorders")
    public ResponseEntity<?> listCustomerOrders(@RequestBody ListCustomerOrdersRequestDTO listCustomerOrdersRequestDTO)
    {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderService.listOrders(customer, listCustomerOrdersRequestDTO);
    }

}
