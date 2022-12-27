package com.readingisgood.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@ApiModel(value = "BookOrderDTO", description = "BookOrderDTO")
public class BookOrderDTO {

    public BookOrderDTO()
    {
    }

    public BookOrderDTO(long bookId, int count)
    {
        this.bookId = bookId;
        this.count = count;
    }

    @JsonProperty("bookId")
    @ApiModelProperty(value = "bookId", example = "1", required = true, position = 0)
    private long bookId;

    @JsonProperty("count")
    @ApiModelProperty(value = "count", example = "4", required = true, position = 1)
    private int count;

    public long getBookId()
    {
        return bookId;
    }

    public void setBookId(long bookId)
    {
        this.bookId = bookId;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }
}
