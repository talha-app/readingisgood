package com.readingisgood.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "BookDTO", description = "BookDTO")
public class BookDTO implements Serializable {
    private static final long serialVersionUID = -1;

    @ApiModelProperty(value = "bookId", example = "1", position = 0)
    private long bookId;

    @ApiModelProperty(value = "title", example = "title", position = 1)
    private String title;

    @ApiModelProperty(value = "author", example = "author", position = 2)
    private String author;

    @ApiModelProperty(value = "price", example = "10.25", position = 3)
    private Double price;

    public long getBookId()
    {
        return bookId;
    }

    public void setBookId(long bookId)
    {
        this.bookId = bookId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }
}
