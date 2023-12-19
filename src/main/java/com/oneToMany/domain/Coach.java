package com.oneToMany.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coach {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private String name;

    // this becomes bi-directional rel because og the mappedBy on the own side
    @ManyToOne
    // this is how to customize the relationship by creating a join table instead of FK reference
    // @JoinTable(name = "coeach_squad", joinColumns = {@JoinColumn(name = "coach_id")}, 
    // inverseJoinColumns = {@JoinColumn(name = "squad_id")})
    private Squad squad;
}
