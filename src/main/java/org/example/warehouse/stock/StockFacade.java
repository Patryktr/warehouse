package org.example.warehouse.stock;

import org.springframework.stereotype.Service;

@Service
public class StockFacade {
    private final StockRepository stockRepository;

    public StockFacade(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }
    public void save(CreateOrUpdateStockCommand createOrUpdateStockCommand) {
        Stock stock = stockRepository.findById(createOrUpdateStockCommand.getProductId()).orElse(null);
        if (stock == null) {
            stock = new Stock(createOrUpdateStockCommand.getProductId(), createOrUpdateStockCommand.getQuantity());
        } else {
            stock.applyChanges(createOrUpdateStockCommand);
        }
        stockRepository.save(stock);
    }

}
