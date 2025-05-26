package org.example.warehouse.product.productDto;

import lombok.Builder;
import lombok.Getter;
import org.example.warehouse.product.abstractPattern.ProductType;
@Builder
@Getter
public class ProductViewDto {
    private final String name;
     private final int weight;
     private final int height;
     private final String description;
    private final ProductType productType;
     private final String category;
     private final String subCategory;
}
