package com.manyToOne.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String zipCode;
    private String street;

    @ManyToOne
    // @JoinColumn ->  when we want the two tables to be joined by a foreign key

    /*
     * with this annotaion, the @ManyToOne relationship will create a table in the database
     * to join the two table (but only address_id will be not null and primary key)
     * 
     * used for a for a normalized table
     */
    @JoinTable(name = "person_address", joinColumns = {@JoinColumn(name = "person_id")}, 
    inverseJoinColumns = {@JoinColumn(name = "address_id")})
    private Person person;
}
