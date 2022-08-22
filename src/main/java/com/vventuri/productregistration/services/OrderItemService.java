package com.vventuri.productregistration.services;

import com.vventuri.productregistration.entities.Item;
import com.vventuri.productregistration.entities.Order;
import com.vventuri.productregistration.entities.OrderItem;
import com.vventuri.productregistration.repositories.ItemRepository;
import com.vventuri.productregistration.repositories.OrderItemRepository;
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
public class OrderItemService {

    @Autowired
    private OrderItemRepository repository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderService orderService;

    public List<OrderItem> findAll() {
        return repository.findAll();
    }

    public OrderItem findById(UUID id) {
        Optional<OrderItem> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public OrderItem createOrderItem(OrderItem oi, UUID id) {
        Optional<Order> order = Optional.ofNullable(orderService.findById(id));
        Optional<Item> obj = Optional.ofNullable(itemService.findItemById(oi.getItemId()));
        oi.setOrder(order.get());
        oi.setTotalValue(oi.getQuantity() * obj.get().getValue());
        oi.setItemId(obj.get().getId());
        return repository.save(oi);
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

    public OrderItem update(UUID id, OrderItem oi) {
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
