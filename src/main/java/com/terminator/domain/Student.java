package com.terminator.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Student {
    
    @Id
    private int id;
    private String name;
    private int age;
}
