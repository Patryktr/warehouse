package org.example.warehouse.stock.stockCommand;

public record UpdateStockCommand(Long productId, Integer quantity, Integer reservedQuantity, boolean isIncremental) {
}
