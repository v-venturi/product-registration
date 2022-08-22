package com.vventuri.productregistration.services;

import com.vventuri.productregistration.entities.Item;
import com.vventuri.productregistration.repositories.ItemRepository;
import com.vventuri.productregistration.services.exceptions.DataBaseException;
import com.vventuri.productregistration.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public List<Item> findAll() {
        return repository.findAll();
    }

    public Item findById(UUID id) {
        Optional<Item> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Item createItem(Item item) {
        return repository.save(item);
    }

    public void delete(UUID id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public Item update(UUID id, Item obj) {
        try {
            Item entity = repository.getOne(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Item entity, Item obj) {
        entity.setDescription(obj.getDescription());
        entity.setValue(obj.getValue());
        entity.setType(obj.getType());
    }
}
