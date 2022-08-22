package com.vventuri.productregistration.entities;

import com.vventuri.productregistration.entities.enums.Type;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_item")
public class Item {


    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private String description;
    private Double value;
    @Enumerated(EnumType.STRING)
    private Type type;

    public Item(UUID id, String description, Double value, Type type) {
        this.id = id;
        this.description = description;
        this.value = value;
        this.type = type;
    }

    public Item() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
