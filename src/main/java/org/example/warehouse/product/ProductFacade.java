package org.example.warehouse.product;

import lombok.RequiredArgsConstructor;
import org.example.warehouse.product.productDto.NewProductDto;
import org.example.warehouse.product.productDto.ProductMapper;
import org.example.warehouse.product.productDto.ProductViewDto;
import org.example.warehouse.product.productDto.UpdateProductDto;
import org.example.warehouse.stock.stockCommand.CreateStockCommand;
import org.example.warehouse.stock.StockFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductFacade {
    private final FactoryProvider factoryProvider;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final StockFacade stockFacade;

    public void save(NewProductDto productDto) {
        Product product = factoryProvider.getByType(productDto.productType()).create(productDto);
        productRepository.save(product);

        CreateStockCommand stockCmd = new CreateStockCommand(product.getId(), 1);
        stockFacade.save(stockCmd);
    }

    public List<ProductDto> getAll() {
        return productRepository.findAll().stream()
                .map(Product::toDto)
                .collect(Collectors.toList());
    }

    public ProductViewDto getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produkt nie znaleziony"));
        return productMapper.mapToViewDto(product);
    }

    public void deleteById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produkt nie znaleziony"));
        productRepository.delete(product);
    }

    public ProductViewDto update(Long id, UpdateProductDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produkt nie znaleziony"));

        if (dto.name() != null) product.changeName(dto.name());
        if (dto.weight() != null) product.changeWeight(dto.weight());
        if (dto.height() != null) product.changeHeight(dto.height());
        if (dto.description() != null) product.changeDescription(dto.description());
        if (dto.category() != null) product.changeCategory(dto.category());
        if (dto.subCategory() != null) product.changeSubCategory(dto.subCategory());

        productRepository.save(product);

        return productMapper.mapToViewDto(product);
    }

}
