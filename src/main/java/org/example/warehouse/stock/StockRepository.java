package org.example.warehouse.stock;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
