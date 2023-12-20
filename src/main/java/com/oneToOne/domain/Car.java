package com.oneToOne.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Car {

    @Id
    private Long id;
    private String mark;
    private String model;

    // uni-directional relationship
    @OneToOne
    private Engine engine;
}
