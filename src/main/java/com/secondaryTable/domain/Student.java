package com.secondaryTable.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SecondaryTable(name = "person_tbl") // this table will be created apart and they'll use share primary key
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(table = "person_tbl") // this field will be mapped to the person_tbl
    private String name;
    @Column(table = "person_tbl") // this field will be added to the person_tbl
    private String ssn;
    private String major;
    private LocalDate entry;
}
