package com.inheritance.joinTable.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

// @Entity -> uncomment to test
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
