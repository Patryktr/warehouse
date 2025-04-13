package org.example.warehouse.abstractPattern.factory;

import org.example.warehouse.product.NewProductDto;
import org.example.warehouse.product.Product;

public interface IAbstractProductFactory {
    Product create(NewProductDto newProductDto);
}
