package org.example.warehouse.product;

import org.example.warehouse.abstractPatterns.ProductType;

public class NewProductDto {
    ProductType productType;
    private String name;
    private int weight;
    private int height;
    private String description;
    private String category;
    private String subCategory;

    public NewProductDto(String name, int weight, int height, String description, ProductType productType, String category, String subCategory) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.description = description;
        this.productType = productType;
        this.category = category;
        this.subCategory = subCategory;

    }
}
