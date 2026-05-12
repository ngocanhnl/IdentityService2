package com.ngocanhdevteria2.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ngocanhdevteria2.demo.dto.request.OrderItemRequest;
import com.ngocanhdevteria2.demo.dto.response.OrderItemResponse;
import com.ngocanhdevteria2.demo.entity.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "order", ignore = true)
	@Mapping(target = "product", ignore = true)
	OrderItem toOrderItem(OrderItemRequest req);

	@Mapping(source = "product.id", target = "productId")
	OrderItemResponse toOrderItemResponse(OrderItem item);
}
