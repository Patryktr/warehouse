package org.example.warehouse.product;

import lombok.Builder;
import lombok.Getter;
@Builder
@Getter
public class ProductDto {
    private final Long productId;
    private final String name;
    private final int weight;
    private final int height;
    private final String description;
    private final String category;

    private final String subCategory;



}
