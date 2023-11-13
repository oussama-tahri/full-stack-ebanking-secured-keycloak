package com.tahrioussama.walletservice.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Currency {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private String symbol;
    private double salePrice;
    private double purchasePrice;
    @OneToMany(mappedBy = "currency")
    private Collection<Country> countries;
}
