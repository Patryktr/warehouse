package org.example.warehouse.abstractPatterns.factory;

import org.example.warehouse.abstractPatterns.sofa.OutdoorSofa;
import org.example.warehouse.product.NewProductDto;
import org.example.warehouse.product.Product;

public class OutdoorFactory implements IAbstractProductFactory {
    private static OutdoorFactory outdoorFactory;

    public static OutdoorFactory get() {
        if (outdoorFactory == null) {
            return new OutdoorFactory();
        }
        return outdoorFactory;
    }

    @Override
    public Product create(NewProductDto newProductDto) {
        return new OutdoorSofa();
    }
}
