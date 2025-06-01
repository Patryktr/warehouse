package org.example.warehouse.product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testChangeName_valid() {
        Product product = new Product();
        product.changeName("Chair");
        assertEquals("Chair", product.getName());
    }

    @Test
    void testChangeName_null_throwsException() {
        Product product = new Product();
        assertThrows(IllegalArgumentException.class, () -> product.changeName(null));
    }

    @Test
    void testChangeHeight_valid() {
        Product product = new Product();
        product.changeHeight(150);
        assertEquals(150, product.getHeight());
    }

    @Test
    void testChangeHeight_negative_throwsException() {
        Product product = new Product();
        assertThrows(IllegalArgumentException.class, () -> product.changeHeight(-5));
    }

    @Test
    void testChangeWeight_valid() {
        Product product = new Product();
        product.changeWeight(20);
        assertEquals(20, product.getWeight());
    }

    @Test
    void testChangeWeight_negative_throwsException() {
        Product product = new Product();
        assertThrows(IllegalArgumentException.class, () -> product.changeWeight(-10));
    }

    @Test
    void testChangeDescription_valid() {
        Product product = new Product();
        product.changeDescription("Nice sofa");
        assertEquals("Nice sofa", product.getDescription());
    }

    @Test
    void testChangeDescription_null_defaultsToEmpty() {
        Product product = new Product();
        product.changeDescription(null);
        assertEquals("", product.getDescription());
    }

    @Test
    void testChangeCategory_valid() {
        Product product = new Product();
        product.changeCategory("Living Room");
        assertEquals("Living Room", product.getCategory());
    }

    @Test
    void testChangeCategory_null_throwsException() {
        Product product = new Product();
        assertThrows(IllegalArgumentException.class, () -> product.changeCategory(null));
    }

    @Test
    void testChangeSubCategory_valid() {
        Product product = new Product();
        product.changeSubCategory("Chairs");
        assertEquals("Chairs", product.getSubCategory());
    }

    @Test
    void testChangeSubCategory_null_throwsException() {
        Product product = new Product();
        assertThrows(IllegalArgumentException.class, () -> product.changeSubCategory(null));
    }

    @Test
    void testToDto_returnsCorrectDto() {
        Product product = new Product();
        product.changeName("Table");
        product.changeHeight(100);
        product.changeWeight(50);
        product.changeDescription("Wooden table");
        product.changeCategory("Kitchen");
        product.changeSubCategory("Tables");

        ProductDto dto = product.toDto();

        assertEquals("Table", dto.getName());
        assertEquals(100, dto.getHeight());
        assertEquals(50, dto.getWeight());
        assertEquals("Wooden table", dto.getDescription());
        assertEquals("Kitchen", dto.getCategory());
        assertEquals("Tables", dto.getSubCategory());
    }
}