package org.example.warehouse.product;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {
    private final ProductFacade productFacade;

    public ProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @PostMapping(value = "/product")
    public void saveItem(@RequestBody ProductDto productDto) {
        productFacade.save(productDto);
    }
    @GetMapping(value = "/products")
    public List<ProductDto> getProducts(){
        return productFacade.getAll();
    }
}
