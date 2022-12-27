package com.readingisgood.entity;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "Stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stockId")
    private long stockId;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId")
    private Book book;

    @Column(name = "count")
    @Min(0)
    private int count;

    @Column(name = "recordDate", nullable = false, updatable = false)
    private LocalDateTime recordDate;

    @Column(name = "lastUpdateDate", nullable = false)
    private LocalDateTime lastUpdateDate;

    @Column(name = "lastUpdatedUser", nullable = false)
    private String lastUpdatedUser;

    public long getStockId()
    {
        return stockId;
    }

    public void setStockId(long stockId)
    {
        this.stockId = stockId;
    }

    public Book getBook()
    {
        return book;
    }

    public void setBook(Book book)
    {
        this.book = book;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public LocalDateTime getRecordDate()
    {
        return recordDate;
    }

    public void setRecordDate(LocalDateTime recordDate)
    {
        this.recordDate = recordDate;
    }

    public LocalDateTime getLastUpdateDate()
    {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate)
    {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdatedUser()
    {
        return lastUpdatedUser;
    }

    public void setLastUpdatedUser(String lastUpdatedUser)
    {
        this.lastUpdatedUser = lastUpdatedUser;
    }
}
