package org.example.warehouse.abstractPatterns;

import org.example.warehouse.abstractPatterns.factory.IAbstractProductFactory;
import org.example.warehouse.abstractPatterns.factory.IndoorFactory;
import org.example.warehouse.abstractPatterns.factory.OutdoorFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class FactoryProvider {
    public final HashMap<ProductType, IAbstractProductFactory> factoryProvider = new HashMap<>();

    public FactoryProvider() {
        this.factoryProvider.put(ProductType.OUTDOOR, new OutdoorFactory());
        this.factoryProvider.put(ProductType.INDOOR, new IndoorFactory());
    }

}
