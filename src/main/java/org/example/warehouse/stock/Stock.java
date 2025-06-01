package org.example.warehouse.stock;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import org.example.warehouse.stock.stockCommand.CreateStockCommand;
import org.example.warehouse.stock.stockDto.StockDto;

@Getter
@Entity

public class Stock {
    @Id
    private Long productId;
    private Integer quantity;
    private Integer reservedQuantity;

    public Stock() {
    }

    public Stock(Long productId, Integer quantity, Integer reservedQuantity) {
        this.productId = productId;
        this.quantity = quantity;
        this.reservedQuantity = reservedQuantity;
    }

    public Stock(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
        this.reservedQuantity = 0;
    }

    public void applyChanges(CreateStockCommand createOrUpdateStockCommand) {
        this.quantity = createOrUpdateStockCommand.quantity();
    }

    public void changeQuantity(Integer quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity can't be negative");
        this.quantity = quantity;
    }

    public void changeReservedQuantity(Integer reservedQuantity) {
        if (reservedQuantity < 0 || reservedQuantity > this.quantity) {
            throw new IllegalArgumentException("Reserved quantity invalid");
        }
        this.reservedQuantity = reservedQuantity;
    }

    public StockDto toDto() {
        return new StockDto(this.productId, this.quantity, this.reservedQuantity);
    }

}
