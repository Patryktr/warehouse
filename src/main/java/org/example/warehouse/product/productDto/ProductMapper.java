package org.example.warehouse.product.productDto;

import org.example.warehouse.product.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {


    public ProductViewDto mapToViewDto(Product product) {
        return ProductViewDto.builder()
                .name(product.getName())
                .weight(product.getWeight())
                .height(product.getHeight())
                .description(product.getDescription())
                .productType(product.getProductType())
                .category(product.getCategory())
                .subCategory(product.getSubCategory())
                .build();
    }



}
