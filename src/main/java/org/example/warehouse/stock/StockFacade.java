package org.example.warehouse.stock;

import lombok.RequiredArgsConstructor;
import org.example.warehouse.stock.stockCommand.CreateStockCommand;
import org.example.warehouse.stock.stockCommand.UpdateStockCommand;
import org.example.warehouse.stock.stockDto.StockDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockFacade {
    private final StockRepository stockRepository;

    public void save(CreateStockCommand cmd) {
        Stock stock = stockRepository.findById(cmd.productId()).orElse(null);
        if (stock == null) {
            stock = new Stock(cmd.productId(), cmd.quantity());
        } else {
            stock.applyChanges(cmd);
        }
        stockRepository.save(stock);
    }

    public StockDto getStockByProductId(Long productId) {
        Stock stock = stockRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produkt nie znaleziony"));
        return stock.toDto();
    }

    public List<StockDto> getAll() {
        return stockRepository.findAll().stream()
                .map(Stock::toDto)
                .collect(Collectors.toList());
    }


    public void updateStock(UpdateStockCommand cmd) {
        Stock stock = stockRepository.findById(cmd.productId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (cmd.quantity() != null) {
            int newQuantity = cmd.isIncremental()
                    ? stock.getQuantity() + cmd.quantity()
                    : cmd.quantity();
            stock.changeQuantity(newQuantity);
        }

        if (cmd.reservedQuantity() != null) {
            int newReserved = cmd.isIncremental()
                    ? stock.getReservedQuantity() + cmd.reservedQuantity()
                    : cmd.reservedQuantity();
            if (newReserved > stock.getQuantity()) {
                throw new IllegalArgumentException("Reserved quantity exceeds stock quantity");
            }
            stock.changeReservedQuantity(newReserved);
        }

        stockRepository.save(stock);
    }

    public void deleteStock(Long productId) {
        if (!stockRepository.existsById(productId)) {
            throw new IllegalArgumentException("Product with id " + productId + " not found in stock.");
        }
        stockRepository.deleteById(productId);
    }


}
