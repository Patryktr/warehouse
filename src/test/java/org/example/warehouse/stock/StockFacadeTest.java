package org.example.warehouse.stock;

import org.example.warehouse.stock.stockCommand.CreateStockCommand;
import org.example.warehouse.stock.stockCommand.UpdateStockCommand;
import org.example.warehouse.stock.stockDto.StockDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StockFacadeTest {

    @InjectMocks
    private StockFacade stockFacade;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateNewStockWhenNotExists() {
        Long productId = 1L;
        CreateStockCommand cmd = new CreateStockCommand(productId, 10);
        when(stockRepository.findById(productId)).thenReturn(Optional.empty());

        stockFacade.save(cmd);

        ArgumentCaptor<Stock> captor = ArgumentCaptor.forClass(Stock.class);
        verify(stockRepository).save(captor.capture());

        assertEquals(10, captor.getValue().getQuantity());
    }

    @Test
    void shouldUpdateExistingStock() {
        Long productId = 1L;
        Stock existing = new Stock(productId, 5);
        CreateStockCommand cmd = new CreateStockCommand(productId, 15);

        when(stockRepository.findById(productId)).thenReturn(Optional.of(existing));

        stockFacade.save(cmd);

        assertEquals(15, existing.getQuantity());
        verify(stockRepository).save(existing);
    }

    @Test
    void shouldGetStockByProductId() {
        Stock stock = new Stock(1L, 10, 2);
        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));

        StockDto dto = stockFacade.getStockByProductId(1L);

        assertEquals(10, dto.quantity());
    }

    @Test
    void shouldThrowWhenStockNotFound() {
        when(stockRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> stockFacade.getStockByProductId(2L));
    }

    @Test
    void shouldUpdateStockWithIncrementalTrue() {
        Stock stock = new Stock(1L, 5, 1);
        UpdateStockCommand cmd = new UpdateStockCommand(1L, 3, 1, true);
        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));

        stockFacade.updateStock(cmd);

        assertEquals(8, stock.getQuantity());
        assertEquals(2, stock.getReservedQuantity());
    }

    @Test
    void shouldDeleteStockWhenExists() {
        when(stockRepository.existsById(1L)).thenReturn(true);

        stockFacade.deleteStock(1L);

        verify(stockRepository).deleteById(1L);
    }

    @Test
    void shouldThrowOnDeleteIfStockNotExists() {
        when(stockRepository.existsById(1L)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> stockFacade.deleteStock(1L));
    }

}