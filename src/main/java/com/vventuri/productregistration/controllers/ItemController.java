package com.vventuri.productregistration.controllers;


import com.vventuri.productregistration.entities.Item;
import com.vventuri.productregistration.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> getAll() {
        return ResponseEntity.ok(itemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getById(@PathVariable UUID id) {
        Item item = itemService.findItemById(id);
        return ResponseEntity.ok().body(item);
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                             .buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(itemService.createItem(item));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable UUID id, @RequestBody Item item){
        return ResponseEntity.ok().body(itemService.update(id, item));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID id){
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }




}
