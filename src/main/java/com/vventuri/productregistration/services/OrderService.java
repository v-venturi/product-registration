package com.vventuri.productregistration.services;

import com.vventuri.productregistration.entities.Item;
import com.vventuri.productregistration.entities.Order;
import com.vventuri.productregistration.entities.OrderItem;
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

import static com.vventuri.productregistration.entities.enums.Type.S;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private ItemService itemService;

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
            Order entity = findById(id);
            entity.setNumber(order.getNumber());
            entity.setDate(order.getDate());
            entity.setPercentageDiscount(order.getPercentageDiscount());
            entity.setTotalValue(order.getTotalValue());
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }


    public Order sellClose(UUID id, Order order) {
        Order auxOrder = findById(id);
        auxOrder.setPercentageDiscount(order.getPercentageDiscount());
        Double total = 0.0;
        for (OrderItem orderItem : auxOrder.getOrderItems()) {
            Item auxItem = itemService.findItemById(orderItem.getItemId());
            if (S.equals(auxItem.getType())) {
                total += orderItem.getTotalValue();
            } else {
                total += (orderItem.getTotalValue() - (orderItem.getTotalValue() * auxOrder.getPercentageDiscount()) / 100);
            }
        }
        auxOrder.setTotalValue(total);
        update(id, auxOrder);
        return auxOrder;
    }

}
