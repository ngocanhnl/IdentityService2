package com.ngocanhdevteria2.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ngocanhdevteria2.demo.dto.request.ApiResponse;
import com.ngocanhdevteria2.demo.dto.request.OrderRequest;
import com.ngocanhdevteria2.demo.dto.response.OrderResponse;
import com.ngocanhdevteria2.demo.service.OrderService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
	OrderService orderService;

	@PostMapping
	ApiResponse<OrderResponse> create(@RequestBody OrderRequest request) {
		return ApiResponse.<OrderResponse>builder()
				.result(orderService.create(request))
				.build();
	}

	@GetMapping
	ApiResponse<List<OrderResponse>> getAll() {
		return ApiResponse.<List<OrderResponse>>builder()
				.result(orderService.getAll())
				.build();
	}

	@GetMapping("/{orderId}")
	ApiResponse<OrderResponse> getById(@PathVariable String orderId) {
		return ApiResponse.<OrderResponse>builder()
				.result(orderService.getById(orderId))
				.build();
	}

	@PutMapping("/{orderId}")
	ApiResponse<OrderResponse> update(@PathVariable String orderId, @RequestBody OrderRequest request) {
		return ApiResponse.<OrderResponse>builder()
				.result(orderService.update(orderId, request))
				.build();
	}

	@DeleteMapping("/{orderId}")
	ApiResponse<Void> delete(@PathVariable String orderId) {
		orderService.delete(orderId);
		return ApiResponse.<Void>builder().build();
	}
}
