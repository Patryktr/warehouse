package org.example.warehouse.stock.stockDto;

public record StockDto(Long productId, Integer quantity,Integer reservedQuantity) {
}
