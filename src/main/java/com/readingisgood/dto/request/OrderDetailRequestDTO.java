package com.readingisgood.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "OrderDetailRequestDTO", description = "OrderDetailRequestDTO")
public class OrderDetailRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "orderId", example = "1", required = true, position = 0)
    private long orderId;

    public long getOrderId()
    {
        return orderId;
    }

    public void setOrderId(long orderId)
    {
        this.orderId = orderId;
    }
}
