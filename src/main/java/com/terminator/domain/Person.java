package com.terminator.domain;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

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
    @Column(name = "fullname", unique = true, length = 199)
    private String name;
    @Temporal(TemporalType.DATE) // by default a date is stored a timestamp, we use @Temoral to change the format
    // in Java, Date represents an instant, and actually represents a millisecond offset from Unix epoch
    private Date subscriptionDate;
    // LocalDate is the date the calendar on the wall says.
    private LocalDate dob; // this one directly map to date
    @Transient // when generating a table, this column will not be generated
    private String token;
}
