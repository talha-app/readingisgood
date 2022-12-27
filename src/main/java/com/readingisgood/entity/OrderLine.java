package com.readingisgood.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "OrderLine")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderLineId")
    private long orderLineId;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId")
    private Order order;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId")
    private Book book;

    @Column(name = "price")
    private Double price;

    @Column(name = "status")
    private Character status;

    @Column(name = "count")
    private int count;

    @Column(name = "recordDate", nullable = false, updatable = false)
    private LocalDateTime recordDate = LocalDateTime.now();

    public long getOrderLineId()
    {
        return orderLineId;
    }

    public void setOrderLineId(long orderLineId)
    {
        this.orderLineId = orderLineId;
    }

    public Order getOrder()
    {
        return order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }

    public Book getBook()
    {
        return book;
    }

    public void setBook(Book book)
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

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }
}
