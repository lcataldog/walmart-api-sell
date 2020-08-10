package com.walmart.api.sell.strategies;

import com.walmart.api.sell.dao.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StrategyFactory {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private StrategyOperationByDescriptionAndBrand strategyOperationByDescriptionAndBrand;

    @Autowired
    private StrategyOperationById strategyOperationById;

    public Strategy getStrategy(String searchProducts) {
        if (searchProducts == null || searchProducts.isEmpty()) {
            throw new IllegalArgumentException("Invalid " + searchProducts);
        }

        Strategy strategy = (searchProducts.matches("[0-9]*")) ? strategyOperationById : strategyOperationByDescriptionAndBrand;

        return strategy;
    }
}
