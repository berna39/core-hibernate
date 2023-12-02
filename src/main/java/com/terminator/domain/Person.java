package com.terminator.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table(name = "people")
@Entity(name = "person")
public class Person{
    
    @Id
    private int id;
    private String name;
    private int age;
}
