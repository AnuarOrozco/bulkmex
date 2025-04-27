package com.bulkmex.repository;

import com.bulkmex.model.Order;
import com.bulkmex.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);
}
