package com.vventuri.productregistration.services;

import com.vventuri.productregistration.entities.Order;
import com.vventuri.productregistration.repositories.OrderRepository;
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
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order findById(UUID id) {
        Optional<Order> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Order createOrder(Order order) {
        return repository.save(order);
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

    public Order update(UUID id, Order order) {
        try {
            Order entity = repository.getOne(id);
            updateData(entity, order);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Order entity, Order obj) {
        entity.setNumber(obj.getNumber());
        entity.setDate(obj.getDate());
        entity.setPercentageDiscount(obj.getPercentageDiscount());
        entity.setTotalValue(obj.getTotalValue());
    }
}
