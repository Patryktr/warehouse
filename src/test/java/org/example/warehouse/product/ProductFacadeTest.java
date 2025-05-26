package org.example.warehouse.product;

import org.example.warehouse.product.abstractPattern.ProductType;
import org.example.warehouse.product.abstractPattern.factory.IAbstractProductFactory;
import org.example.warehouse.product.productDto.NewProductDto;
import org.example.warehouse.product.productDto.ProductMapper;
import org.example.warehouse.product.productDto.ProductViewDto;
import org.example.warehouse.product.productDto.UpdateProductDto;
import org.example.warehouse.stock.StockFacade;
import org.example.warehouse.stock.stockCommand.CreateStockCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductFacadeTest {
    private FactoryProvider factoryProvider;
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private StockFacade stockFacade;

    private ProductFacade productFacade;

    @BeforeEach
    void setUp() {
        factoryProvider = mock(FactoryProvider.class);
        productRepository = mock(ProductRepository.class);
        productMapper = mock(ProductMapper.class);
        stockFacade = mock(StockFacade.class);


        productFacade = new ProductFacade(factoryProvider, productRepository, productMapper, stockFacade);
    }

    @Test
    void shouldSaveProductAndCreateStock() {
        // given
        NewProductDto dto = new NewProductDto("Phone", 100, 200, "desc", ProductType.OUTDOOR, "Electronics", "Phones");
        Product product = mock(Product.class);
        IAbstractProductFactory factory = mock(IAbstractProductFactory.class);
        product = mock(Product.class);

        when(factoryProvider.getByType(ProductType.OUTDOOR)).thenReturn(factory);
        when(factory.create(dto)).thenReturn(product);
        when(product.getId()).thenReturn(1L);

        // when
        productFacade.save(dto);

        // then
        verify(productRepository).save(product);
        verify(stockFacade).save(new CreateStockCommand(1L, 1));
    }
    @Test
    void shouldReturnAllProducts() {
        // Given
        Product product1 = mock(Product.class);
        Product product2 = mock(Product.class);

        when(productRepository.findAll()).thenReturn(List.of(product1, product2));


        ProductDto dto1 = ProductDto.builder()
                .productId(1L)
                .name("prod1")
                .weight(10)
                .height(5)
                .description("desc1")
                .category("cat1")
                .subCategory("subcat1")
                .build();

        ProductDto dto2 = ProductDto.builder()
                .productId(2L)
                .name("prod2")
                .weight(20)
                .height(15)
                .description("desc2")
                .category("cat2")
                .subCategory("subcat2")
                .build();

        when(product1.toDto()).thenReturn(dto1);
        when(product2.toDto()).thenReturn(dto2);

        // When
        List<ProductDto> result = productFacade.getAll();

        // Then
        assertEquals(2, result.size());
        assertEquals("prod1", result.get(0).getName());
        assertEquals(10, result.get(0).getWeight());
        assertEquals("cat1", result.get(0).getCategory());

        assertEquals("prod2", result.get(1).getName());
        assertEquals(20, result.get(1).getWeight());
        assertEquals("cat2", result.get(1).getCategory());

        verify(productRepository).findAll();
    }
    @Test
    void shouldUpdateProductAndReturnViewDto() {
        // given
        Long id = 42L;
        Product product = mock(Product.class);
        UpdateProductDto dto = new UpdateProductDto(
                id,
                "NewName",
                15,
                25,
                "NewDescription",
                ProductType.OUTDOOR,
                "NewCategory",
                "NewSubCategory"
        );

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        ProductViewDto expectedView = ProductViewDto.builder()
                .name("NewName")
                .weight(15)
                .height(25)
                .description("NewDescription")
                .productType(ProductType.OUTDOOR)
                .category("NewCategory")
                .subCategory("NewSubCategory")
                .build();

        when(productMapper.mapToViewDto(product)).thenReturn(expectedView);

        // when
        ProductViewDto result = productFacade.update(id, dto);

        // then —
        verify(product).changeName("NewName");
        verify(product).changeWeight(15);
        verify(product).changeHeight(25);
        verify(product).changeDescription("NewDescription");
        verify(product).changeCategory("NewCategory");
        verify(product).changeSubCategory("NewSubCategory");
        // zapis i mapowanie
        verify(productRepository).save(product);
        verify(productMapper).mapToViewDto(product);
        // wynik
        assertEquals(expectedView, result);
    }



}