package org.example.warehouse.product;

import lombok.Getter;

@Getter
public class ProductDto {
    private final String name;
    private final Long productId;

    public ProductDto(String name, Long productId) {
        this.name = name;
        this.productId = productId;

    }

}
