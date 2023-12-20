package com.oneToOne.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
    
    @Id
    private Long id;
    private String name;
    private String country;

    @Embedded
    private BillingInfo billingInfo;
}
