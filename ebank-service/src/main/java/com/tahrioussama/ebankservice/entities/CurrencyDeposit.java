package com.tahrioussama.ebankservice.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class CurrencyDeposit {
    @Id
    private String currencyId;
    private double saleCurrencyPrice;
    private double purchaseCurrencyPrice;
    private double balance;
    @OneToMany(mappedBy = "currencyDeposit")
    private List<CurrencyTransaction> currencyTransactions;
}
