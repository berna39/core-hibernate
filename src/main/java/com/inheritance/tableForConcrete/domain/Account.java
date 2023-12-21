package com.inheritance.tableForConcrete.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Account {
    
    @Id
    // how to customize the sequence
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // cannot use identity for idenity
    private Long id;
    private double balance;
}
