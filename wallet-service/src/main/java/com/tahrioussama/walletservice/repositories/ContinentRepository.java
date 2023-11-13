package com.tahrioussama.walletservice.repositories;

import com.tahrioussama.walletservice.entities.Continent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContinentRepository extends JpaRepository<Continent,Long> {
    Continent findByContinentName(String continentName);
}
