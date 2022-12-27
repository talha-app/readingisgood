package com.readingisgood.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "UpdateStockRequestDTO", description = "UpdateStockRequestDTO")
public class UpdateStockRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "bookId", example = "1", required = true, position = 0)
    private long bookId;

    @ApiModelProperty(value = "stockCount", example = "10", required = true, position = 1)
    private int stockCount;

    public long getBookId()
    {
        return bookId;
    }

    public void setBookId(long bookId)
    {
        this.bookId = bookId;
    }

    public int getStockCount()
    {
        return stockCount;
    }

    public void setStockCount(int stockCount)
    {
        this.stockCount = stockCount;
    }
}
