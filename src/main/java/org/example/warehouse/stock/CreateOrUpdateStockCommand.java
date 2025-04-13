package org.example.warehouse.stock;

import lombok.Getter;

@Getter
public class CreateOrUpdateStockCommand {
    private Long productId;
    private Integer quantity;
}
