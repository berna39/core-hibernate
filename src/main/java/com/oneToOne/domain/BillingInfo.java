package com.oneToOne.domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BillingInfo {
    // no primary key for embeddable entity
    private String iban;
    private String cvv;
}
