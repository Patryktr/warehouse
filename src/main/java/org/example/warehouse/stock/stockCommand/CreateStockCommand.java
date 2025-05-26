package org.example.warehouse.stock.stockCommand;

public record CreateStockCommand(Long productId, Integer quantity) {}

