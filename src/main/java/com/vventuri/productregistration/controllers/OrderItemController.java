package com.vventuri.productregistration.controllers;


import com.vventuri.productregistration.entities.Item;
import com.vventuri.productregistration.entities.OrderItem;
import com.vventuri.productregistration.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/orderItems")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<List<OrderItem>> getAll() {
        return ResponseEntity.ok(orderItemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getById(@PathVariable UUID id) {
        OrderItem order = orderItemService.findById(id);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem oi, @PathVariable UUID id) {
        oi = orderItemService.createOrderItem(oi, id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("id")
                                             .buildAndExpand(oi.getId()).toUri();
        return ResponseEntity.created(uri).body(oi);

    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable UUID id, @RequestBody OrderItem oi) {
        return ResponseEntity.ok().body(orderItemService.update(id, oi));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable UUID id) {
        orderItemService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
