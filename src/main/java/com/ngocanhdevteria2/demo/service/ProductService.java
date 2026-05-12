package com.ngocanhdevteria2.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ngocanhdevteria2.demo.dto.request.ProductRequest;
import com.ngocanhdevteria2.demo.dto.response.ProductResponse;
import com.ngocanhdevteria2.demo.exception.AppException;
import com.ngocanhdevteria2.demo.exception.ErrorCode;
import com.ngocanhdevteria2.demo.mapper.ProductMapper;
import com.ngocanhdevteria2.demo.repository.ProductRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
	ProductRepository productRepository;
	ProductMapper productMapper;

	public ProductResponse create(ProductRequest req) {
		var product = productMapper.toProduct(req);
		product = productRepository.save(product);
		return productMapper.toProductResponse(product);
	}

	public List<ProductResponse> getAll() {
		return productRepository.findAll().stream().map(productMapper::toProductResponse).toList();
	}

	public ProductResponse getById(String productId) {
		var product = productRepository.findById(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTS));
		return productMapper.toProductResponse(product);
	}

	public ProductResponse update(String productId, ProductRequest req) {
		var product = productRepository.findById(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTS));
		productMapper.updateProduct(product, req);
		product = productRepository.save(product);
		return productMapper.toProductResponse(product);
	}

	public void delete(String productId) {
		if (!productRepository.existsById(productId)) {
			throw new AppException(ErrorCode.PRODUCT_NOT_EXISTS);
		}
		productRepository.deleteById(productId);
	}
}
