package com.vventuri.productregistration.services.exceptions;

import com.vventuri.productregistration.entities.OrderItem;
import com.vventuri.productregistration.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository repository;

    public List<OrderItem> findAll() {
        return repository.findAll();
    }

    public OrderItem findById(Long id) {
        Optional<OrderItem> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public OrderItem createOrderItem(OrderItem oi) {
        return repository.save(oi);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public OrderItem update(Long id, OrderItem oi) {
        try {
            OrderItem entity = repository.getOne(id);
            updateData(entity, oi);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(OrderItem entity, OrderItem oi) {
        entity.setItemId(oi.getItemId());
        entity.setQuantity(oi.getQuantity());

    }
}