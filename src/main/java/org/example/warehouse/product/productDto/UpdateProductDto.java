package org.example.warehouse.product.productDto;

import org.example.warehouse.product.abstractPattern.ProductType;

public record UpdateProductDto(
        Long id,
        String name,
        Integer weight,
        Integer height,
        String description,
        ProductType productType,
        String category,
        String subCategory
) {}
