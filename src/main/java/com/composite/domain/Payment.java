package com.composite.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;

    // in this case we're referencing to a table that has composite PK
    // hibernate will add both fields of the composite key in this table on db side
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "order_legecy_number_custom", referencedColumnName = "legacyNumber"),
        @JoinColumn(name = "order_delivery_number_custom", referencedColumnName = "deliveryNumber")
    })
    private Order order;
}
