package com.vventuri.productregistration.entities;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
public class OrderItem implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Order orderId;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "item_id")
    private Item itemId;
    private Double quantity;
    private Double totalValue;
 //TODO constructors/getter/setters


    public OrderItem() {
    }

    public OrderItem(Long id, Order orderId, Item itemId, Double quantity, Double totalValue) {
        this.id = id;
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.totalValue = totalValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }
}
