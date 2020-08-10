package com.walmart.api.sell.strategies;

import com.walmart.api.sell.dao.ProductRepository;
import com.walmart.api.sell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StrategyOperationByDescriptionAndBrand implements Strategy {

    @Autowired
    private ProductRepository repository;

    @Value("${strategyOperationByDescriptionAndBrand.lengthSearchedProduct}")
    private int lengthSearchedProduct;


    @Override
    public List<Product> getProducts(String searchProducts) {
        return repository.findByDescriptionLikeOrBrandLike(searchProducts, searchProducts);
    }

    @Override
    public int getLengthSearchedProduct(){
        return lengthSearchedProduct;
    };
}
