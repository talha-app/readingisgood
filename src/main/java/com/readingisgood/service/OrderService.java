package com.readingisgood.service;

import com.readingisgood.dto.BookOrderDTO;
import com.readingisgood.dto.OrderDTO;
import com.readingisgood.dto.OrderDetailDTO;
import com.readingisgood.dto.request.CreateOrderRequestDTO;
import com.readingisgood.dto.request.ListCustomerOrdersByDateRequestDTO;
import com.readingisgood.dto.request.ListCustomerOrdersRequestDTO;
import com.readingisgood.dto.request.OrderDetailRequestDTO;
import com.readingisgood.dto.response.MessageResponse;
import com.readingisgood.dto.response.MonthlyStatisticsResponseDTO;
import com.readingisgood.entity.Book;
import com.readingisgood.entity.Customer;
import com.readingisgood.entity.Order;
import com.readingisgood.entity.OrderLine;
import com.readingisgood.enums.OrderStatus;
import com.readingisgood.enums.Status;
import com.readingisgood.mapper.IOrderMapper;
import com.readingisgood.repository.IBookRepository;
import com.readingisgood.repository.IOrderLineRepository;
import com.readingisgood.repository.IOrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.InvalidParameterException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final IOrderRepository orderRepository;
    private final IOrderLineRepository orderLineRepository;
    private final IBookRepository bookRepository;
    private final StockService stockService;
    private final IOrderMapper orderMapper;
    private static final Logger logger = LogManager.getLogger(OrderService.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");


    public OrderService(IOrderRepository orderRepository, IOrderLineRepository orderLineRepository, IBookRepository bookRepository, StockService stockService, IOrderMapper orderMapper)
    {
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
        this.bookRepository = bookRepository;
        this.stockService = stockService;
        this.orderMapper = orderMapper;
    }

    public ResponseEntity<MessageResponse> createOrder(CreateOrderRequestDTO requestDTO, Customer customer) throws Exception
    {
        if (isValidOrder(requestDTO))
        {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Wrong book count"));
        }
        Order order = prepareOrderEntity(customer);
        List<OrderLine> orderLines = prepareOrderLines(order, requestDTO.getBookOrders());
        setOrderTotalPrice(order, orderLines);
        stockService.updateStock(orderLines);
        orderRepository.save(order);
        orderLineRepository.saveAll(orderLines);
        logger.info("Order is created successfully!");
        return ResponseEntity.ok(new MessageResponse("Order is created successfully!"));
    }

    private boolean isValidOrder(CreateOrderRequestDTO requestDTO)
    {
        return requestDTO.getBookOrders().stream().anyMatch(bookOrderDTO -> bookOrderDTO.getCount() < 1);
    }


    private void setOrderTotalPrice(Order order, List<OrderLine> orderLines)
    {
        Double totalPrice = orderLines.stream().map(OrderLine::getPrice).reduce(0D, Double::sum);
        Double truncatedTotalPrice = BigDecimal.valueOf(totalPrice).setScale(4, RoundingMode.HALF_UP).doubleValue();
        order.setTotalPrice(truncatedTotalPrice);
    }

    private Order prepareOrderEntity(Customer customer)
    {
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderStatus(OrderStatus.WAITING);
        order.setLastUpdatedUser("MOBILEAPP");
        order.setOrderDate(LocalDateTime.now());
        order.setLastUpdateDate(LocalDateTime.now());

        return order;
    }

    private List<OrderLine> prepareOrderLines(Order order, List<BookOrderDTO> bookOrders)
    {
        if (bookOrders.isEmpty())
        {
            logger.error("BookId is empty");
            throw new InvalidParameterException("BookId is empty");
        }
        return bookOrders.stream().map(bookOrder -> createOrderLine(bookOrder, order)).collect(Collectors.toList());
    }

    private OrderLine createOrderLine(BookOrderDTO bookOrder, Order order)
    {
        long bookId = bookOrder.getBookId();
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (!bookOptional.isPresent())
        {
            logger.error("Invalid BookId:");
            throw new InvalidParameterException("Invalid BookId: " + bookId);
        }

        Book book = bookOptional.get();
        OrderLine orderLine = new OrderLine();
        orderLine.setBook(book);
        orderLine.setCount(bookOrder.getCount());
        orderLine.setPrice(calculateOrderLinePrice(book.getPrice(), bookOrder.getCount()));
        orderLine.setStatus(Status.ACTIVE.getVal());
        orderLine.setOrder(order);
        return orderLine;
    }

    private double calculateOrderLinePrice(double price, int count)
    {
        return new BigDecimal(price).setScale(2, RoundingMode.HALF_EVEN).multiply(new BigDecimal(count)).doubleValue();
    }

    public ResponseEntity<?> listOrders(Customer customer, ListCustomerOrdersRequestDTO listCustomerOrdersRequestDTO)
    {
        Pageable pageRequest = PageRequest.of(listCustomerOrdersRequestDTO.getPage(), listCustomerOrdersRequestDTO.getSize());
        List<Order> orders = orderRepository.findByCustomerOrderByOrderDate(customer, pageRequest);
        if (orders.isEmpty())
        {
            logger.debug("There is no placed order!");
            return ResponseEntity.ok(new MessageResponse("There is no placed order!"));
        }
        List<OrderDTO> orderDTOs = orders.stream().map(orderMapper::toOrderDTO).collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOs);
    }

    public ResponseEntity<OrderDetailDTO> getOrderDetail(OrderDetailRequestDTO requestDTO, Customer customer)
    {
        long orderId = requestDTO.getOrderId();
        Optional<Order> orderOptional = orderRepository.findById(orderId);

        if (!isOrderBelongsToCustomer(customer.getCustomerId(), orderOptional))
        {
            logger.error("Error: orderId is wrong!");
            throw new InvalidParameterException("Error: orderId is wrong!");
        }
        OrderDetailDTO orderDetailDTO = orderMapper.toOrderDetailDTO(orderOptional.get());
        return ResponseEntity.ok(orderDetailDTO);
    }

    private boolean isOrderBelongsToCustomer(long customerId, Optional<Order> orderOptional)
    {
        if (orderOptional.isPresent() && orderOptional.get().getCustomer().getCustomerId() == customerId)
        {
            return true;
        }
        return false;
    }

    public ResponseEntity<?> listCustomerOrdersByDate(Customer customer, ListCustomerOrdersByDateRequestDTO requestDTO)
    {
        LocalDateTime startDate = LocalDateTime.parse(requestDTO.getStartDate(), formatter);
        LocalDateTime endDate = LocalDateTime.parse(requestDTO.getEndDate(), formatter);

        List<Order> orders = orderRepository.findByCustomerAndOrderDateBetweenOrderByOrderDate(customer, startDate, endDate);
        if (orders.isEmpty())
        {
            logger.debug("There is no placed order!");
            return ResponseEntity.ok(new MessageResponse("There is no placed order!"));
        }
        List<OrderDTO> orderDTOs = orders.stream().map(orderMapper::toOrderDTO).collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOs);
    }

    public ResponseEntity<?> getMonthlyStatistics(Customer customer)
    {
        List<MonthlyStatisticsResponseDTO> monthlyStatisticsResponseDTOs = new ArrayList<>();
        Object[][] monthlyStatistics = orderRepository.getMonthlyStatistics(customer.getCustomerId());
        for (Object[] monthlyStatistic : monthlyStatistics)
        {
            monthlyStatisticsResponseDTOs.add(convertMonthlyStatistics(monthlyStatistic));
        }
        return ResponseEntity.ok(monthlyStatisticsResponseDTOs);
    }

    private MonthlyStatisticsResponseDTO convertMonthlyStatistics(Object[] monthlyStatistics)
    {
        Month month = ((Timestamp) monthlyStatistics[0]).toLocalDateTime().getMonth();
        int totalOrderCount = ((BigInteger) monthlyStatistics[1]).intValue();
        int totalBookCount = ((BigDecimal) monthlyStatistics[2]).intValue();
        double totalOrderAmount = (double) monthlyStatistics[3];
        return new MonthlyStatisticsResponseDTO(month, totalOrderCount, totalBookCount, totalOrderAmount);
    }
}
