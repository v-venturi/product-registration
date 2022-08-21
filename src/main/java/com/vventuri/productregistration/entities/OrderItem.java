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

}
