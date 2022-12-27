package com.readingisgood.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Book")
public class Book {

    public Book()
    {
    }

    public Book(String title, String author, Double price, LocalDateTime recordDate)
    {
        this.title = title;
        this.author = author;
        this.price = price;
        this.recordDate = recordDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookId")
    private long bookId;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "price")
    private Double price;

    @Column(name = "recordDate")
    private LocalDateTime recordDate;

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

    public LocalDateTime getRecordDate()
    {
        return recordDate;
    }

    public void setRecordDate(LocalDateTime recordDate)
    {
        this.recordDate = recordDate;
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
