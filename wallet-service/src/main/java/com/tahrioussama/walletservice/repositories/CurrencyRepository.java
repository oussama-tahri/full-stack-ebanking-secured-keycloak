package com.tahrioussama.walletservice.repositories;


import com.tahrioussama.walletservice.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency,Long> {
    Currency findByCode(String code);
    List<Currency> findByNameContains(String name);
}
