package org.example.warehouse.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;


@Entity
@Getter
public  class Product {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

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
        return new ProductDto(name, id);
    }
}
