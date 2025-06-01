package org.example.warehouse.stock;

import org.example.warehouse.stock.stockCommand.UpdateStockCommand;
import org.example.warehouse.stock.stockDto.StockDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StockControllerTest {
    private StockFacade stockFacade;
    private StockController stockController;

    @BeforeEach
    public void setUp() {
        stockFacade = Mockito.mock(StockFacade.class);
        stockController = new StockController(stockFacade);
    }

    @Test
    @DisplayName("GET /stock/ - Should return all stocks")
    public void shouldReturnAllStocks() {
        // Given
        StockDto stock1 = new StockDto(1L, 10, 2);
        StockDto stock2 = new StockDto(2L, 5, 1);

        when(stockFacade.getAll()).thenReturn(Arrays.asList(stock1, stock2));

        // When
        List<StockDto> result = stockController.getStock();

        // Then
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).productId());
        assertEquals(10, result.get(0).quantity());
        verify(stockFacade, times(1)).getAll();
    }

    @Test
    @DisplayName("GET /stock/{id} - Should return stock by ID")
    public void shouldReturnStockById() {
        // Given
        StockDto stock = new StockDto(1L, 10, 2);
        when(stockFacade.getStockByProductId(1L)).thenReturn(stock);

        // When
        StockDto result = stockController.getStock(1L);

        // Then
        assertEquals(1L, result.productId());
        assertEquals(10, result.quantity());
        verify(stockFacade, times(1)).getStockByProductId(1L);
    }

    @Test
    @DisplayName("PUT /stock/ - Should update stock")
    public void shouldUpdateStock() {
        // Given
        UpdateStockCommand cmd = new UpdateStockCommand(1L, 20, 5, false);
        doNothing().when(stockFacade).updateStock(cmd);

        // When
        stockController.updateStock(cmd);

        // Then
        verify(stockFacade, times(1)).updateStock(cmd);
    }

    @Test
    @DisplayName("DELETE /stock/{id} - Should delete stock by ID")
    public void shouldDeleteStockById() {
        // Given
        doNothing().when(stockFacade).deleteStock(1L);

        // When
        stockController.deleteStock(1L);

        // Then
        verify(stockFacade, times(1)).deleteStock(1L);
    }

}