package com.vventuri.productregistration.repositories;

import com.vventuri.productregistration.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
