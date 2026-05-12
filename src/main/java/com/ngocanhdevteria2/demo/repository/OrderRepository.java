package com.ngocanhdevteria2.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngocanhdevteria2.demo.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {}
