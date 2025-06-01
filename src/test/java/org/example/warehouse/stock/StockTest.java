package org.example.warehouse.stock;

import org.example.warehouse.stock.stockCommand.CreateStockCommand;
import org.example.warehouse.stock.stockDto.StockDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {
    @Test
    void testApplyChanges() {
        Stock stock = new Stock(1L, 5);
        stock.applyChanges(new CreateStockCommand(1L, 10));
        assertEquals(10, stock.getQuantity());
    }

    @Test
    void testChangeQuantity() {
        Stock stock = new Stock(1L, 5);
        stock.changeQuantity(8);
        assertEquals(8, stock.getQuantity());
    }

    @Test
    void testChangeQuantityThrowsException() {
        Stock stock = new Stock(1L, 5);
        assertThrows(IllegalArgumentException.class, () -> stock.changeQuantity(-1));
    }

    @Test
    void testChangeReservedQuantity() {
        Stock stock = new Stock(1L, 10);
        stock.changeReservedQuantity(5);
        assertEquals(5, stock.getReservedQuantity());
    }

    @Test
    void testChangeReservedQuantityThrowsException() {
        Stock stock = new Stock(1L, 5);
        assertThrows(IllegalArgumentException.class, () -> stock.changeReservedQuantity(6));
    }

    @Test
    void testToDto() {
        Stock stock = new Stock(1L, 10, 2);
        StockDto dto = stock.toDto();
        assertEquals(1L, dto.productId());
        assertEquals(10, dto.quantity());
        assertEquals(2, dto.reservedQuantity());
    }

}