package com.tahrioussama.ebankservice.repository;

import com.tahrioussama.ebankservice.entities.CurrencyDeposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankCurrencyDepositRepository extends JpaRepository<CurrencyDeposit, String> {
}
