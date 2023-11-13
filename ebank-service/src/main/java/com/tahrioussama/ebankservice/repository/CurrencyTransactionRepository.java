package com.tahrioussama.ebankservice.repository;

import com.tahrioussama.ebankservice.entities.CurrencyTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyTransactionRepository extends JpaRepository<CurrencyTransaction, String> {
}
