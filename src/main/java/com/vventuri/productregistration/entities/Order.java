package com.vventuri.productregistration.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;


@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;
    private Double percentageDiscount;
    private Double totalValue;

    public Order(Long id, Integer number, Date date, Double percentageDiscount, Double totalValue) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.percentageDiscount = percentageDiscount;
        this.totalValue = totalValue;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(Double percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }
}
