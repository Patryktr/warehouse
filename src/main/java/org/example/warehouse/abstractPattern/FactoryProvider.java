package org.example.warehouse.abstractPattern;

import org.example.warehouse.abstractPattern.factory.IAbstractProductFactory;
import org.example.warehouse.abstractPattern.factory.IndoorFactory;
import org.example.warehouse.abstractPattern.factory.OutdoorFactory;
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
