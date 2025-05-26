package org.example.warehouse.product.abstractPattern.factory;

import org.example.warehouse.product.abstractPattern.ProductType;
import org.example.warehouse.product.abstractPattern.sofa.OutdoorSofa;
import org.example.warehouse.product.productDto.NewProductDto;
import org.example.warehouse.product.Product;
import org.springframework.stereotype.Service;

@Service
public class OutdoorFactory implements IAbstractProductFactory {

	@Override
	public Product create(NewProductDto dto) {
		OutdoorSofa sofa = new OutdoorSofa();

		sofa.changeName(dto.name());
		sofa.changeCategory(dto.category());
		sofa.changeSubCategory(dto.subCategory());
		sofa.changeDescription(dto.description());
		sofa.changeHeight(dto.height());
		sofa.changeWeight(dto.weight());



		return sofa;
	}

	@Override
	public ProductType getType() {
		return ProductType.OUTDOOR;
	}
}
