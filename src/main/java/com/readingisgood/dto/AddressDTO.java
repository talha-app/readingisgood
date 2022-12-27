package com.readingisgood.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "AddressDTO", description = "AddressDTO")
public class AddressDTO implements Serializable {
    private static final long serialVersionUID = -1;

    @ApiModelProperty(value = "address", example = "Besiktas", required = true, position = 0)
    private String address;

    @ApiModelProperty(value = "city", example = "Istanbul", required = true, position = 1)
    private String city;

    @ApiModelProperty(value = "country", example = "Turkey", required = true, position = 2)
    private String country;

    @ApiModelProperty(value = "postalCode", example = "12345", required = true, position = 3)
    private String postalCode;

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
}
