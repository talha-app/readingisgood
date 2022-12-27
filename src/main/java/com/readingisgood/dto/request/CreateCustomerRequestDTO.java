package com.readingisgood.dto.request;

import com.readingisgood.dto.AddressDTO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class CreateCustomerRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "username", example = "admin", required = true, position = 0)
    private String username;

    @ApiModelProperty(value = "password", example = "password", required = true, position = 1)
    private String password;

    @ApiModelProperty(value = "name", example = "John", required = true, position = 2)
    private String name;

    @ApiModelProperty(value = "surname", example = "Smith", required = true, position = 3)
    private String surname;

    @ApiModelProperty(value = "email", example = "john@smith.com", required = true, position = 3)
    private String email;

    @ApiModelProperty(value = "phone", example = "90555555555", required = true, position = 4)
    private String phone;

    @ApiModelProperty(value = "address", required = true, position = 5)
    private AddressDTO address;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public AddressDTO getAddress()
    {
        return address;
    }

    public void setAddress(AddressDTO address)
    {
        this.address = address;
    }
}
