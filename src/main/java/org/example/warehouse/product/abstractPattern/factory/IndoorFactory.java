package org.example.warehouse.product.abstractPattern.factory;

import org.example.warehouse.product.abstractPattern.ProductType;
import org.example.warehouse.product.abstractPattern.sofa.IndoorSofa;
import org.example.warehouse.product.abstractPattern.sofa.OutdoorSofa;
import org.example.warehouse.product.productDto.NewProductDto;
import org.example.warehouse.product.Product;
import org.springframework.stereotype.Service;

@Service
public class IndoorFactory implements IAbstractProductFactory {

	@Override
	public Product create(NewProductDto newProductDto) {
		IndoorSofa sofa = new IndoorSofa();

		sofa.changeName(newProductDto.name());
		sofa.changeCategory(newProductDto.category());
		sofa.changeSubCategory(newProductDto.subCategory());
		sofa.changeDescription(newProductDto.description());
		sofa.changeHeight(newProductDto.height());
		sofa.changeWeight(newProductDto.weight());



		return sofa;

	}

	@Override
	public ProductType getType() {
		return ProductType.INDOOR;
	}

}
