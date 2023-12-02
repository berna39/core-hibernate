package com.terminator.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table(name = "people")
@Entity(name = "person")
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    // @GeneratedValue(strategy = GenerationType.SEQUENCE) : to use a sequence
    // @GeneratedValue(strategy = GenerationType.TABLE) : to use a table -> emulate sequence for dbms wich does not support sequences
    private Long id;
    private String name;
    private int age;
}
