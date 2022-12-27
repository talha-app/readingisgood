package com.readingisgood.mapper;


import com.readingisgood.dto.OrderDTO;
import com.readingisgood.dto.OrderDetailDTO;
import com.readingisgood.entity.Order;
import org.mapstruct.Mapper;

@Mapper(implementationName = "OrderMapperImpl", componentModel = "spring")
public interface IOrderMapper {
    Order toOrder(OrderDTO orderDTO);

    OrderDTO toOrderDTO(Order order);

    Order toOrder(OrderDetailDTO orderDetailDTO);

    OrderDetailDTO toOrderDetailDTO(Order order);

}
