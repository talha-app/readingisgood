package com.readingisgood.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addressId")
    private long addressId;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "postalCode")
    private String postalCode;

    @Column(name = "recordDate", nullable = false, updatable = false)
    private LocalDateTime recordDate = LocalDateTime.now();

    public long getAddressId()
    {
        return addressId;
    }

    public void setAddressId(long addressId)
    {
        this.addressId = addressId;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public LocalDateTime getRecordDate()
    {
        return recordDate;
    }

    public void setRecordDate(LocalDateTime recordDate)
    {
        this.recordDate = recordDate;
    }

}
