package com.readingisgood.dto.request;

import com.readingisgood.dto.AddressDTO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class CreateNewBookRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "author", example = "Rustin Chicotti", required = true, position = 0)
    private String author;

    @ApiModelProperty(value = "title", example = "Rhabdomys pumilio", required = true, position = 1)
    private String title;

    @ApiModelProperty(value = "price", example = "5.25", required = true, position = 2)
    private double price;


    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }
}
