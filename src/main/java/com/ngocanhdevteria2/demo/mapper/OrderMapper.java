package com.ngocanhdevteria2.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ngocanhdevteria2.demo.dto.request.OrderRequest;
import com.ngocanhdevteria2.demo.dto.response.OrderResponse;
import com.ngocanhdevteria2.demo.entity.Order;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public interface OrderMapper {

	OrderResponse toOrderResponse(Order order);

	@Mapping(target = "items", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "totalPrice", ignore = true)
	@Mapping(target = "orderDate", ignore = true)
	Order toOrder(OrderRequest req);

	@Mapping(target = "items", ignore = true)
	@Mapping(target = "totalPrice", ignore = true)
	@Mapping(target = "orderDate", ignore = true)
	void updateOrder(@MappingTarget Order order, OrderRequest req);
}
