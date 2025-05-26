package org.example.warehouse.product;

import io.swagger.v3.oas.annotations.Operation;
import org.example.warehouse.product.productDto.NewProductDto;
import org.example.warehouse.product.productDto.ProductViewDto;
import org.example.warehouse.product.productDto.UpdateProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController(value = "/product")
public class ProductController {
    private final ProductFacade productFacade;

    public ProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @PostMapping(value = "/")
    @Operation(summary = "Create a new Product", description = "Adds new Product to DB")
    public void createProduct(@RequestBody NewProductDto productDto) {
        productFacade.save(productDto);
    }

    @GetMapping(value = "/")
    @Operation(summary = "Return all Product", description = "Return a list of all products from DB")
    public List<ProductDto> getProducts() {
        return productFacade.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a Product", description = "Get product by id")
    public ProductViewDto getProduct(@PathVariable Long id) {
        return productFacade.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Product", description = "Update a product by id")
    public void updateProduct(@PathVariable Long id, @RequestBody UpdateProductDto dto) {
        productFacade.update(id, dto);

    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Deleting a product by id from DB")
    public void deleteProduct(@PathVariable Long id) {
        productFacade.deleteById(id);
    }


}

