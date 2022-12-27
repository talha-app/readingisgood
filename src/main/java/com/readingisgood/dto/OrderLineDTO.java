package com.readingisgood.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

@ApiModel(value = "OrderLineDTO", description = "OrderLineDTO")
public class OrderLineDTO implements Serializable {
    private static final long serialVersionUID = -1;

    @ApiModelProperty(value = "orderLineId", example = "0", position = 0)
    private long orderLineId;

    @ApiModelProperty(value = "book", position = 1)
    private BookDTO book;

    @ApiModelProperty(value = "price", example = "20.25", position = 2)
    private Double price;

    @ApiModelProperty(value = "status", example = "A", position = 3)
    private Character status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "recordDate", example = "2022-12-26 21:18:18.362522", position = 4)
    private LocalDateTime recordDate;

    public long getOrderLineId()
    {
        return orderLineId;
    }

    public void setOrderLineId(long orderLineId)
    {
        this.orderLineId = orderLineId;
    }

    public BookDTO getBook()
    {
        return book;
    }

    public void setBook(BookDTO book)
    {
        this.book = book;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public Character getStatus()
    {
        return status;
    }

    public void setStatus(Character status)
    {
        this.status = status;
    }

    public LocalDateTime getRecordDate()
    {
        return recordDate;
    }

    public void setRecordDate(LocalDateTime recordDate)
    {
        this.recordDate = recordDate;
    }
}
