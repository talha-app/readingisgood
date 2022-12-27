package com.readingisgood.entity;

import com.readingisgood.enums.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private long orderId;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId")
    private Customer customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderLine> orderLines;

    @Column(name = "orderStatus")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "orderDate", nullable = false, updatable = false)
    private LocalDateTime orderDate;

    @Column(name = "lastUpdateDate", nullable = false)
    private LocalDateTime lastUpdateDate;

    @Column(name = "lastUpdatedUser", nullable = false)
    private String lastUpdatedUser;

    @Column(name = "totalPrice")
    private Double totalPrice;

    public void addOrderLine(OrderLine orderLine)
    {
        if (orderLines == null)
        {
            orderLines = new ArrayList<>();
        }
        orderLines.add(orderLine);
    }

    public long getOrderId()
    {
        return orderId;
    }

    public void setOrderId(long orderId)
    {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate)
    {
        this.orderDate = orderDate;
    }

    public LocalDateTime getLastUpdateDate()
    {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate)
    {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdatedUser()
    {
        return lastUpdatedUser;
    }

    public void setLastUpdatedUser(String lastUpdatedUser)
    {
        this.lastUpdatedUser = lastUpdatedUser;
    }

    public OrderStatus getOrderStatus()
    {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public List<OrderLine> getOrderLines()
    {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines)
    {
        this.orderLines = orderLines;
    }

    public Double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice)
    {
        this.totalPrice = totalPrice;
    }
}
