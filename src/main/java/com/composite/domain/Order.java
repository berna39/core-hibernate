package com.composite.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "orders")
// @IdClass(OrderId.class) -> 1st method
public class Order {
    
    // 1st method:

    // @Id
    // private String legacyNumber;
    // @Id
    // private String deliveryNumber;

    // 2nd method
    @EmbeddedId
    private OrderId orderId;

    private String deliveryAddress;
    private Double amount;
}
