package com.manyToMany.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Producer {
    
    @Id
    private Long id;
    private String name;
    
    // uni-directional
    @ManyToMany
    private List<Movie> movies;
}
