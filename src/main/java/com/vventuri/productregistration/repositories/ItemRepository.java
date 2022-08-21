package com.vventuri.productregistration.repositories;

import com.vventuri.productregistration.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
