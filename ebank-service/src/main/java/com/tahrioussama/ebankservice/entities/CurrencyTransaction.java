package com.tahrioussama.ebankservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tahrioussama.ebankservice.enums.TransactionType;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class CurrencyTransaction {
    @Id
    private String id;
    private Date timestamp;
    private double amount;
    private String currencyCode;
    private String walletId;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private double currencyPrice;
    private double costs;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private CurrencyDeposit currencyDeposit;
    @OneToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private CurrencyTransaction originTransaction;
}
