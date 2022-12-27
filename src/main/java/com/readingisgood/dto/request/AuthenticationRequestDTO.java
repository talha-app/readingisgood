package com.readingisgood.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "AuthenticationRequestDTO", description = "AuthenticationRequestDTO")
public class AuthenticationRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    public AuthenticationRequestDTO()
    {
    }

    public AuthenticationRequestDTO(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    @ApiModelProperty(value = "username", example = "admin", required = true, position = 0)
    private String username;

    @ApiModelProperty(value = "password", example = "123456", required = true, position = 1)
    private String password;

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
}
