package com.vventuri.productregistration.services;

import com.vventuri.productregistration.entities.Item;
import com.vventuri.productregistration.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public List<Item> findAll(){
        return repository.findAll();
    }
}
