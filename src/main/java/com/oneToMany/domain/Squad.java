package com.oneToMany.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Squad {
    
    @Id
    @Column(name = "squadada_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // uni-directional
    @OneToMany
    @JoinColumn(name = "squad_id")
    private List<Player> players;

    // bidirectional
    @OneToMany(mappedBy = "squad")
    private List<Coach> coaches;
}
