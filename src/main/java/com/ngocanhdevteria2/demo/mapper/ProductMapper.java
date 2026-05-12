package com.ngocanhdevteria2.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.ngocanhdevteria2.demo.dto.request.ProductRequest;
import com.ngocanhdevteria2.demo.dto.response.ProductResponse;
import com.ngocanhdevteria2.demo.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	Product toProduct(ProductRequest req);

	ProductResponse toProductResponse(Product product);

	void updateProduct(@MappingTarget Product product, ProductRequest req);
}
