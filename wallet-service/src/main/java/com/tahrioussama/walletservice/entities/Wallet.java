package com.tahrioussama.walletservice.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Wallet {
    @Id
    private String id;
    private long createdAt;
    private String userId;
    private double balance;
    @ManyToOne
    private Currency currency;
    @OneToMany(mappedBy = "wallet")
    private List<WalletTransaction> walletTransactions;
}
