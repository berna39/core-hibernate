package com.inheritance.singleTable.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "svg_account")
public class Saving extends Account {
    
    private double intrest;

    public Saving(double balance, double intrest) {
        super(null, balance);
        this.intrest = intrest;
    }

    public double getIntrest() {
        return intrest;
    }
    public void setIntrest(double intrest) {
        this.intrest = intrest;
    }
}
