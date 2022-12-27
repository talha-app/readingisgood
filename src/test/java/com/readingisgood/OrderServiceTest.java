package com.readingisgood;

import com.readingisgood.dto.BookOrderDTO;
import com.readingisgood.dto.OrderDTO;
import com.readingisgood.dto.OrderDetailDTO;
import com.readingisgood.dto.request.CreateCustomerRequestDTO;
import com.readingisgood.dto.request.CreateOrderRequestDTO;
import com.readingisgood.dto.request.ListCustomerOrdersRequestDTO;
import com.readingisgood.dto.request.OrderDetailRequestDTO;
import com.readingisgood.dto.response.MessageResponse;
import com.readingisgood.entity.Customer;
import com.readingisgood.repository.ICustomerRepository;
import com.readingisgood.repository.IOrderRepository;
import com.readingisgood.service.CustomerService;
import com.readingisgood.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    private static final String TEST_USERNAME = "testUser";
    private static Customer customer;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Before
    public void setup()
    {
        boolean isTestUserCreated = customerRepository.existsByUsername(TEST_USERNAME);
        if (!isTestUserCreated)
        {
            CreateCustomerRequestDTO createCustomerRequestDTO = TestUtil.prepareCreateCustomerRequestDTO();
            customerService.createCustomer(createCustomerRequestDTO);
        }
        customer = customerRepository.findByUsername(TEST_USERNAME).get();
    }

    @Test
    @Order(1)
    public void createOrder() throws Exception
    {
        CreateOrderRequestDTO requestDTO = prepareCreateOrderRequestDTO();
        ResponseEntity<MessageResponse> responseEntity = orderService.createOrder(requestDTO, customer);
        assertThat(responseEntity.getStatusCode().equals(HttpStatus.OK)).isTrue();
    }

    @Test
    @Order(2)
    public void getOrders()
    {
        ListCustomerOrdersRequestDTO listCustomerOrdersRequestDTO = new ListCustomerOrdersRequestDTO(0, 10);
        List<OrderDTO> orderDTOs = (List<OrderDTO>) orderService.listOrders(customer, listCustomerOrdersRequestDTO).getBody();
        assertThat(orderDTOs.isEmpty()).isFalse();
    }

    @Test
    @Order(3)
    public void getOrderDetail()
    {
        ListCustomerOrdersRequestDTO listCustomerOrdersRequestDTO = new ListCustomerOrdersRequestDTO(0, 10);
        List<OrderDTO> orderDTOs = (List<OrderDTO>) orderService.listOrders(customer, listCustomerOrdersRequestDTO).getBody();
        long lastOrderId = orderDTOs.get(orderDTOs.size() - 1).getOrderId();

        OrderDetailRequestDTO requestDTO = new OrderDetailRequestDTO();
        requestDTO.setOrderId(lastOrderId);
        OrderDetailDTO orderDetailDTO = orderService.getOrderDetail(requestDTO, customer).getBody();

        assertThat(orderDetailDTO).isNotNull();
    }

    private CreateOrderRequestDTO prepareCreateOrderRequestDTO()
    {
        List<BookOrderDTO> bookOrders = new ArrayList<>();
        bookOrders.add(new BookOrderDTO(1,1));
        bookOrders.add(new BookOrderDTO(2,1));
        bookOrders.add(new BookOrderDTO(3,1));
        CreateOrderRequestDTO createOrderRequestDTO = new CreateOrderRequestDTO(bookOrders);
        return createOrderRequestDTO;
    }

}
