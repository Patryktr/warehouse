package org.example.warehouse.product;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProductFacade {
    private final ProductRepository productRepository;

    public ProductFacade(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public void save(ProductDto productDto) {productRepository.save(new Product(productDto));}

    public List<ProductDto> getAll() {
        return productRepository.findAll().stream()
                .map(Product::toDto)
                .collect(Collectors.toList());
    }
}
