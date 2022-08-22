package com.vventuri.productregistration.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_order_item")
public class OrderItem {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "order_id")
    private Order orderId;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "item_id")
    private Item itemId;
    private Double quantity;
    private Double totalValue;


    public OrderItem() {
    }

    public OrderItem(UUID id, Order orderId, Item itemId, Double quantity, Double totalValue) {
        this.id = id;
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.totalValue = totalValue;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
