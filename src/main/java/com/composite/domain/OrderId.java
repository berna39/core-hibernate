package com.composite.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderId implements Serializable {
    
    private String legacyNumber;
    private String deliveryNumber;
}
