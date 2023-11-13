package com.tahrioussama.walletservice.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Continent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String continentName;
    @OneToMany(mappedBy = "continent")
    private List<Country> countries;

}
