package org.example.warehouse.product.productDto;

import org.example.warehouse.product.abstractPattern.ProductType;

public record NewProductDto(String name,
							int weight,
							int height,
							String description,
							ProductType productType,
							String category,
							String subCategory) {
}
