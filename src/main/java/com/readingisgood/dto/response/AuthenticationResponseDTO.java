package com.readingisgood.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "AuthenticationResponseDTO", description = "AuthenticationResponseDTO")
public class AuthenticationResponseDTO implements Serializable {
    private static final long serialVersionUID = -1;

    @ApiModelProperty(value = "jwtToken", position = 0)
    private final String jwtToken;

    public AuthenticationResponseDTO(String jwtToken)
    {
        this.jwtToken = jwtToken;
    }

    public String getToken()
    {
        return this.jwtToken;
    }
}
