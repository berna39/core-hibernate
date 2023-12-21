package com.inheritance.singleTable.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "chk_account")
public class Checking extends Account {
    
    @Column(name = "[limit]")
    private double limit;

    public Checking(double balance, double limit) {
        super(null, balance);
        this.limit = limit;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }
}
