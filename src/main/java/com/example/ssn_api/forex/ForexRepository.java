package com.example.ssn_api.forex;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ForexRepository extends JpaRepository<ForexEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT rate from forex_rates where forex_rates.from = :from  and forex_rates.to = :to")
    Float getExchangeRate(@Param("from") String from, @Param("to") String to);

    @Query(nativeQuery = true, value = "SELECT * from forex_rates where forex_rates.from = :from  and forex_rates.to = :to")
    ForexEntity getExchangeEntity(@Param("from") String from, @Param("to") String to);

}
