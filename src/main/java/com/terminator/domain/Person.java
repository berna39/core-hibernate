package com.terminator.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class Person {
    
    @Id
    private int id;
    private String name;
    private int age;
}
