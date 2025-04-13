package org.example.warehouse.stock;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

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
    public void applyChanges(CreateOrUpdateStockCommand createOrUpdateStockCommand){
        this.quantity = createOrUpdateStockCommand.getQuantity();
    }

}
