package com.readingisgood.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "ListCustomerOrdersRequestDTO", description = "ListCustomerOrdersRequestDTO")
public class ListCustomerOrdersRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public ListCustomerOrdersRequestDTO()
    {
    }

    public ListCustomerOrdersRequestDTO(int page, int size)
    {
        this.page = page;
        this.size = size;
    }

    @ApiModelProperty(value = "page", example = "0", required = true, position = 0)
    private int page;

    @ApiModelProperty(value = "size", example = "10", required = true, position = 1)
    private int size;

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }
}
