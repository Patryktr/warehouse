package org.example.warehouse.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.warehouse.product.Product;
import org.example.warehouse.product.ProductRepository;
import org.example.warehouse.product.abstractPattern.ProductType;
import org.example.warehouse.product.productDto.NewProductDto;
import org.example.warehouse.product.productDto.UpdateProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class ProductIT {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;


    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Test
    void shouldCreateProduct() throws Exception {
        NewProductDto dto = new NewProductDto(
                "Stół kuchenny",
                40,
                75,
                "Stół z drewna bukowego",
                ProductType.INDOOR,
                "Meble",
                "Stoły"
        );

        mockMvc.perform(post("/product/")
                        .with(httpBasic("admin", "123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getName()).isEqualTo("Stół kuchenny");
    }

    @Test
    void shouldGetProductById() throws Exception {
        Product product = new Product(null, "Szafa");
        product.changeHeight(200);
        product.changeWeight(50);
        product.changeDescription("Pojemna szafa z drzwiami przesuwnymi");
        product.changeCategory("Meble");
        product.changeSubCategory("Szafy");
        productRepository.save(product);

        mockMvc.perform(get("/product/{id}", product.getId())
                        .with(httpBasic("user", "123")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Szafa"))
                .andExpect(jsonPath("$.height").value(200));
    }

    @Test
    void shouldGetAllProducts() throws Exception {
        Product product1 = new Product(null, "Krzesło");
        product1.changeHeight(90);
        product1.changeWeight(10);
        product1.changeDescription("Krzesło tapicerowane");
        product1.changeCategory("Meble");
        product1.changeSubCategory("Krzesła");

        Product product2 = new Product(null, "Stolik kawowy");
        product2.changeHeight(45);
        product2.changeWeight(15);
        product2.changeDescription("Stolik z blatem szklanym");
        product2.changeCategory("Meble");
        product2.changeSubCategory("Stoliki");

        productRepository.saveAll(List.of(product1, product2));

        mockMvc.perform(get("/product/")
                        .with(httpBasic("user", "123")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void shouldUpdateProduct() throws Exception {
        Product product = new Product(null, "Komoda");
        product.changeHeight(100);
        product.changeWeight(40);
        product.changeDescription("Drewniana komoda");
        product.changeCategory("Meble");
        product.changeSubCategory("Komody");
        productRepository.save(product);

        UpdateProductDto updateDto = new UpdateProductDto(
                product.getId(),
                "Komoda biała",
                42,
                105,
                "Komoda w stylu skandynawskim",
                ProductType.INDOOR,
                "Meble",
                "Komody"
        );

        mockMvc.perform(put("/product/{id}", product.getId())
                        .with(httpBasic("admin", "123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk());

        Product updated = productRepository.findById(product.getId()).orElseThrow();
        assertThat(updated.getName()).isEqualTo("Komoda biała");
        assertThat(updated.getHeight()).isEqualTo(105);
        assertThat(updated.getDescription()).contains("skandynawskim");
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        Product product = new Product(null, "Biurko");
        product.changeCategory("Meble");
        product.changeSubCategory("Biurka");
        productRepository.save(product);

        mockMvc.perform(delete("/product/{id}", product.getId())
                        .with(httpBasic("admin", "123")))
                .andExpect(status().isOk());

        assertThat(productRepository.existsById(product.getId())).isFalse();
    }



    @Test
    void shouldReturnForbiddenForUnauthorizedDelete() throws Exception {
        Product product = new Product(null, "Regał");
        product.changeCategory("Meble");
        product.changeSubCategory("Regały");
        productRepository.save(product);

        mockMvc.perform(delete("/product/{id}", product.getId())
                        .with(httpBasic("user", "123")))
                .andExpect(status().isForbidden());
    }
}
