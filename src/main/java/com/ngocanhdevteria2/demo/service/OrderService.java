package com.ngocanhdevteria2.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ngocanhdevteria2.demo.dto.request.OrderItemRequest;
import com.ngocanhdevteria2.demo.dto.request.OrderRequest;
import com.ngocanhdevteria2.demo.dto.response.OrderResponse;
import com.ngocanhdevteria2.demo.entity.Order;
import com.ngocanhdevteria2.demo.entity.OrderItem;
import com.ngocanhdevteria2.demo.entity.Product;
import com.ngocanhdevteria2.demo.exception.AppException;
import com.ngocanhdevteria2.demo.exception.ErrorCode;
import com.ngocanhdevteria2.demo.mapper.OrderItemMapper;
import com.ngocanhdevteria2.demo.mapper.OrderMapper;
import com.ngocanhdevteria2.demo.repository.OrderRepository;
import com.ngocanhdevteria2.demo.repository.ProductRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional(readOnly = true)
public class OrderService {

	OrderRepository orderRepository;
	ProductRepository productRepository;
	OrderMapper orderMapper;
	OrderItemMapper orderItemMapper;

	@Transactional
	public OrderResponse create(OrderRequest req) {
		requireItems(req.getItems());

		var order = orderMapper.toOrder(req);
		order.setOrderDate(LocalDateTime.now());
		replaceItems(order, req.getItems());
		order.setTotalPrice(sumLineTotals(order.getItems()));

		order = orderRepository.save(order);
		return orderMapper.toOrderResponse(order);
	}

	public List<OrderResponse> getAll() {
		return orderRepository.findAll().stream().map(orderMapper::toOrderResponse).toList();
	}

	public OrderResponse getById(String orderId) {
		var order = orderRepository.findById(orderId).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTS));
		return orderMapper.toOrderResponse(order);
	}

	@Transactional
	public OrderResponse update(String orderId, OrderRequest req) {
		requireItems(req.getItems());

		var order = orderRepository.findById(orderId).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTS));

		orderMapper.updateOrder(order, req);
		order.getItems().clear();
		replaceItems(order, req.getItems());
		order.setTotalPrice(sumLineTotals(order.getItems()));

		order = orderRepository.save(order);
		return orderMapper.toOrderResponse(order);
	}

	@Transactional
	public void delete(String orderId) {
		if (!orderRepository.existsById(orderId)) {
			throw new AppException(ErrorCode.ORDER_NOT_EXISTS);
		}
		orderRepository.deleteById(orderId);
	}

	private void requireItems(List<OrderItemRequest> items) {
		if (items == null || items.isEmpty()) {
			throw new AppException(ErrorCode.ORDER_ITEMS_REQUIRED);
		}
	}

	private void replaceItems(Order order, List<OrderItemRequest> itemRequests) {
		for (OrderItemRequest itemReq : itemRequests) {
			validateLine(itemReq);

			Product product = productRepository
					.findById(itemReq.getProductId())
					.orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTS));

			OrderItem item = orderItemMapper.toOrderItem(itemReq);
			item.setProduct(product);
			item.setOrder(order);
			order.getItems().add(item);
		}
	}

	private void validateLine(OrderItemRequest itemReq) {
		if (itemReq.getQuantity() == null || itemReq.getQuantity() <= 0) {
			throw new AppException(ErrorCode.INVALID_ORDER_QUANTITY);
		}
		if (itemReq.getPrice() == null || itemReq.getPrice().signum() < 0) {
			throw new AppException(ErrorCode.INVALID_ORDER_PRICE);
		}
	}

	private BigDecimal sumLineTotals(List<OrderItem> items) {
		return items.stream()
				.map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
