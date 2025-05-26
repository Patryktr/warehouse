package org.example.warehouse.product;

import org.example.warehouse.product.abstractPattern.ProductType;
import org.example.warehouse.product.productDto.NewProductDto;
import org.example.warehouse.product.productDto.ProductViewDto;
import org.example.warehouse.product.productDto.UpdateProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;


class ProductControllerTest {

    private ProductController productController;
    private ProductFacade productFacade;

    @BeforeEach
    public void setUp() {
        productFacade = Mockito.mock(ProductFacade.class);
        productController = new ProductController(productFacade);
    }

    @Test
    void shouldCreateProduct() {
        NewProductDto dto = new NewProductDto(
                "Name",
                1,
                2,
                "desc",
                ProductType.OUTDOOR,
                "catagory",
                "sub");

        productController.createProduct(dto);

        Mockito.verify(productFacade).save(dto);
    }

    @Test
    void shouldReturnAllProducts() {
        List<ProductDto> expected = List.of(new ProductDto(
                1L,
                "Name",
                1,
                2,
                "desc",
                "catagory",
                "sub"));
        Mockito.when(productFacade.getAll()).thenReturn(expected);

        List<ProductDto> result = productController.getProducts();

        Assertions.assertEquals(expected, result);
        Mockito.verify(productFacade).getAll();
    }
    @Test
    void shouldGetProductById() {
        ProductViewDto expected = ProductViewDto.builder()
                .name("Example Product")
                .weight(10)
                .height(20)
                .description("Example Description")
                .productType(ProductType.OUTDOOR)
                .category("Electronics")
                .subCategory("Phones")
                .build();
        Mockito.when(productFacade.getById(1L)).thenReturn(expected);

        ProductViewDto result = productController.getProduct(1L);

        Assertions.assertEquals(expected, result);
        Mockito.verify(productFacade).getById(1L);
    }
    @Test
    void shouldUpdateProduct() {
        UpdateProductDto dto = new UpdateProductDto(
                1L,
                "Updated Product",
                12,
                25,
                "Updated description",
                ProductType.OUTDOOR,
                "Electronics",
                "Smartphones"
        );

        productController.updateProduct(1L, dto);

        Mockito.verify(productFacade).update(1L, dto);
    }

    @Test
    void shouldDeleteProduct() {
        productController.deleteProduct(1L);

        Mockito.verify(productFacade).deleteById(1L);
    }
    @Test
    void createProduct() {
    }

    @Test
    void getProducts() {
        //given

    }

    @Test
    void getProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}