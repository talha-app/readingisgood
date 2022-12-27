package com.readingisgood.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.readingisgood.dto.BookOrderDTO;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "CreateOrderRequestDTO", description = "CreateOrderRequestDTO")
public class CreateOrderRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("bookOrders")
    private List<BookOrderDTO> bookOrders;

    public CreateOrderRequestDTO()
    {

    }

    public CreateOrderRequestDTO(List<BookOrderDTO> bookOrders)
    {
        this.bookOrders = bookOrders;
    }

    public List<BookOrderDTO> getBookOrders()
    {
        return bookOrders;
    }

    public void setBookOrders(List<BookOrderDTO> bookOrders)
    {
        this.bookOrders = bookOrders;
    }

}
