package com.readingisgood.dto.response;

import java.time.Month;

public class MonthlyStatisticsResponseDTO {

    private Month month;

    private int totalOrderCount;

    private int totalBookCount;

    private double totalOrderAmount;

    public MonthlyStatisticsResponseDTO()
    {
    }

    public MonthlyStatisticsResponseDTO(Month month, int totalOrderCount, int totalBookCount, double totalOrderAmount)
    {
        this.month = month;
        this.totalOrderCount = totalOrderCount;
        this.totalBookCount = totalBookCount;
        this.totalOrderAmount = totalOrderAmount;
    }

    public Month getMonth()
    {
        return month;
    }

    public void setMonth(Month month)
    {
        this.month = month;
    }

    public int getTotalOrderCount()
    {
        return totalOrderCount;
    }

    public void setTotalOrderCount(int totalOrderCount)
    {
        this.totalOrderCount = totalOrderCount;
    }

    public int getTotalBookCount()
    {
        return totalBookCount;
    }

    public void setTotalBookCount(int totalBookCount)
    {
        this.totalBookCount = totalBookCount;
    }

    public double getTotalOrderAmount()
    {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(double totalOrderAmount)
    {
        this.totalOrderAmount = totalOrderAmount;
    }
}
