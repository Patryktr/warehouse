package org.example.warehouse.product.abstractPattern.factory;

import org.example.warehouse.product.abstractPattern.ProductType;
import org.example.warehouse.product.productDto.NewProductDto;
import org.example.warehouse.product.Product;

public interface IAbstractProductFactory {
    Product create(NewProductDto newProductDto);

    ProductType getType();
}
