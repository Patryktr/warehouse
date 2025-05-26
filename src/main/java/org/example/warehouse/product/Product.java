package org.example.warehouse.product;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.warehouse.product.abstractPattern.ProductType;


@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")

@Table(name = "product")
public  class Product {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int weight;
    private int height;
    private String description;
    ProductType productType;
    private String category;
    private String subCategory;

    public Product() {
    }

    public Product(Long productId, String name) {
        this.id = productId;
        this.name = name;
    }

    public Product(ProductDto productDto) {
        this.id = productDto.getProductId();
        this.name = productDto.getName();

    }
    public ProductDto toDto() {
        return ProductDto.builder()
                .productId(this.id)
                .name(this.name)
                .weight(this.weight)
                .height(this.height)
                .description(this.description)
                .category(this.category)
                .subCategory(this.subCategory)
                .build();
    }

    public void changeName(String newName) {
        if (newName == null) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = newName;
    }

    public void changeHeight(int newHeight) {
        if (newHeight < 0) {
            throw new IllegalArgumentException("Height cannot be negative");
        }
        this.height = newHeight;
    }
    public void changeWeight(int newWeight) {
        if (newWeight < 0) {
            throw new IllegalArgumentException("Height cannot be negative");
        }
        this.weight = newWeight;
    }

    public void changeDescription(String newDescription) {
        this.description = newDescription != null ? newDescription : "";
    }

    public void changeCategory(String newCategory) {
        if (newCategory == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        this.category = newCategory;
    }

    public void changeSubCategory(String newSubCategory) {
        if (newSubCategory == null) {
            throw new IllegalArgumentException("SubCategory cannot be null");
        }
        this.subCategory = newSubCategory;
    }





}
