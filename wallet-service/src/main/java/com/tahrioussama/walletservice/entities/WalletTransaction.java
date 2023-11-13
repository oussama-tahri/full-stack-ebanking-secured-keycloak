package com.tahrioussama.walletservice.entities;

import com.tahrioussama.walletservice.enums.WalletTransactionType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class WalletTransaction {
    @Id
    private String id;
    private long timestamp;
    private double amount;
    private double currencyPrice;
    @Enumerated(EnumType.STRING)
    private WalletTransactionType type;
    @ManyToOne
    private Wallet wallet;
    @ManyToOne
    private Wallet targetWallet;
}
