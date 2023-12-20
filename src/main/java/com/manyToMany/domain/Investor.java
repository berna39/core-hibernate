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
public class Investor {
    
    @Id
    private Long id;
    private String name;

    // bi-directional relationship
    @ManyToMany(mappedBy = "investors")
    // as a join table is created by default, 'can customize it by using @JoinTable and sure we remove the mappedBy param
    // @JoinTable(name = "investor_company", joinColumns = {@JoinColumn(name="investor_id")}, 
    // inverseJoinColumns = {@JoinColumn(name="company_id")})
    private List<Company> companies;
}
