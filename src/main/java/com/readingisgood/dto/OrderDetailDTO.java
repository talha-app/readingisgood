package com.readingisgood.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.readingisgood.enums.OrderStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@ApiModel(value = "OrderDetailDTO", description = "OrderDetailDTO")
public class OrderDetailDTO implements Serializable {
    private static final long serialVersionUID = -1;

    @ApiModelProperty(value = "orderId", example = "1", position = 0)
    private long orderId;

    @ApiModelProperty(value = "orderStatus", example = "WAITING", position = 1)
    private OrderStatus orderStatus;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "orderDate", example = "2022-12-26 21:18:18.362522", position = 2)
    private LocalDateTime orderDate;

    @ApiModelProperty(value = "totalPrice", example = "20.25", position = 3)
    private Double totalPrice;

    @ApiModelProperty(value = "orderLines", position = 4)
    private List<OrderLineDTO> orderLines;

    public long getOrderId()
    {
        return orderId;
    }

    public void setOrderId(long orderId)
    {
        this.orderId = orderId;
    }

    public OrderStatus getOrderStatus()
    {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate)
    {
        this.orderDate = orderDate;
    }

    public Double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public List<OrderLineDTO> getOrderLines()
    {
        return orderLines;
    }

    public void setOrderLines(List<OrderLineDTO> orderLines)
    {
        this.orderLines = orderLines;
    }
}
