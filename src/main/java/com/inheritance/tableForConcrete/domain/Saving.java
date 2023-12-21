package com.inheritance.tableForConcrete.domain;

import javax.persistence.Entity;

@Entity
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
