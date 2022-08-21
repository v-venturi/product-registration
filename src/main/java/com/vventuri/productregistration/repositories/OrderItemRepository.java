package com.vventuri.productregistration.repositories;

import com.vventuri.productregistration.entities.Order;
import com.vventuri.productregistration.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
