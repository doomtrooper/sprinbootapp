package com.morganstanley.anand.repository;

import com.morganstanley.anand.model.Trade;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, String> {
    List<Trade> findByBrokerCode(String brokerCode);
    @Query("Select t.stockName FROM Trade t GROUP BY stockName ORDER BY sum(quantity) DESC")
    List<String> findTopStock();
    List<Trade> findByBrokerCodeAndStockName(String brokerCode, String stockName);
}
