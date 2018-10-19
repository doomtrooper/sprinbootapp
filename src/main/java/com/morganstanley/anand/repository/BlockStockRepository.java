package com.morganstanley.anand.repository;

import com.morganstanley.anand.model.BlacklistStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockStockRepository extends JpaRepository<BlacklistStock, String> {
}
