package org.example.warehouse.product;

import org.example.warehouse.product.abstractPattern.ProductType;
import org.example.warehouse.product.abstractPattern.factory.IAbstractProductFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
class FactoryProvider {

	private final Map<ProductType, IAbstractProductFactory> factoryProviderMap = new HashMap<>();

	public FactoryProvider(List<IAbstractProductFactory> productFactories) {
		productFactories.forEach(productFactory -> factoryProviderMap.put(productFactory.getType(), productFactory));
	}

	IAbstractProductFactory getByType(ProductType productType) {
		return this.factoryProviderMap.get(productType);
	}

}
