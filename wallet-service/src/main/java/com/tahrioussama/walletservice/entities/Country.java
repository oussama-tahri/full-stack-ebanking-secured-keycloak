package com.tahrioussama.walletservice.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Country {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String countryName;
    private int m49Code;
    private String isoCode;
    @ManyToOne
    private Continent continent;
    @ManyToOne
    private Currency currency;
    private double longitude;
    private double latitude;
    private double altitude;
}
