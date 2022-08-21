package com.vventuri.productregistration.controllers;


import com.vventuri.productregistration.entities.OrderItem;
import com.vventuri.productregistration.services.exceptions.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<OrderItem> getById(@PathVariable Long id) {
        OrderItem order = orderItemService.findById(id);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem oi) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                             .buildAndExpand(oi.getId()).toUri();
        return ResponseEntity.created(uri).body(orderItemService.createOrderItem(oi));

    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id, @RequestBody OrderItem oi){
        return ResponseEntity.ok().body(orderItemService.update(id, oi));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id){
        orderItemService.delete(id);
        return ResponseEntity.noContent().build();
    }




}
