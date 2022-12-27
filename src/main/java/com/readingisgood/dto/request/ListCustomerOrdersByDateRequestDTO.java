package com.readingisgood.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(value = "ListCustomerOrdersByDateRequestDTO", description = "OrderDetailRequestDTO")
public class ListCustomerOrdersByDateRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "startDate", example = "26-12-2022 19:00", required = true, position = 0)
    @NotNull
    private String startDate;

    @ApiModelProperty(value = "endDate", example = "27-12-2022 19:00", required = true, position = 1)
    @NotNull
    private String endDate;

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
}
