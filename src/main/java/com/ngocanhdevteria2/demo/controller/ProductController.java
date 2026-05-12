package com.ngocanhdevteria2.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ngocanhdevteria2.demo.dto.request.ApiResponse;
import com.ngocanhdevteria2.demo.dto.request.ProductRequest;
import com.ngocanhdevteria2.demo.dto.response.ProductResponse;
import com.ngocanhdevteria2.demo.service.ProductService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
	ProductService productService;

	@PostMapping
	ApiResponse<ProductResponse> create(@RequestBody ProductRequest request) {
		return ApiResponse.<ProductResponse>builder()
				.result(productService.create(request))
				.build();
	}

	@GetMapping
	ApiResponse<List<ProductResponse>> getAll() {
		return ApiResponse.<List<ProductResponse>>builder()
				.result(productService.getAll())
				.build();
	}

	@GetMapping("/{productId}")
	ApiResponse<ProductResponse> getById(@PathVariable String productId) {
		return ApiResponse.<ProductResponse>builder()
				.result(productService.getById(productId))
				.build();
	}

	@PutMapping("/{productId}")
	ApiResponse<ProductResponse> update(@PathVariable String productId, @RequestBody ProductRequest request) {
		return ApiResponse.<ProductResponse>builder()
				.result(productService.update(productId, request))
				.build();
	}

	@DeleteMapping("/{productId}")
	ApiResponse<Void> delete(@PathVariable String productId) {
		productService.delete(productId);
		return ApiResponse.<Void>builder().build();
	}
}
