package org.example.warehouse.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController(value = "/product")
public class ProductController {
    private final ProductFacade productFacade;

    public ProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @PostMapping(value = "/")
    public void createProduct(@RequestBody ProductDto productDto) {
        productFacade.save(productDto);
    }

    @GetMapping(value = "/")
    public List<ProductDto> getProducts() {
        return productFacade.getAll();
    }
}
