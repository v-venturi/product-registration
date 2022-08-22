package com.vventuri.productregistration.repositories;

import com.vventuri.productregistration.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
}
