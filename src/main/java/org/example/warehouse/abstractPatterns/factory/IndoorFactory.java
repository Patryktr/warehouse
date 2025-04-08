package org.example.warehouse.abstractPatterns.factory;

import org.example.warehouse.abstractPatterns.sofa.IndoorSofa;
import org.example.warehouse.product.NewProductDto;
import org.example.warehouse.product.Product;

public class IndoorFactory implements IAbstractProductFactory {

    private static IndoorFactory indoorFactory;

    public static IndoorFactory get() {
        if (indoorFactory == null) {
            return new IndoorFactory();
        }
        return indoorFactory;
    }

    @Override
    public Product create(NewProductDto newProductDto) {
        return new IndoorSofa();
    }
}
