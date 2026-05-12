package com.ngocanhdevteria2.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	String customerName;
	BigDecimal totalPrice;
	LocalDateTime orderDate;

	@Builder.Default
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	List<OrderItem> items = new ArrayList<>();
}
