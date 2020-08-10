package com.walmart.api.sell.service;

import com.walmart.api.sell.model.Product;
import com.walmart.api.sell.strategies.Strategy;
import com.walmart.api.sell.strategies.StrategyFactory;
import com.walmart.api.sell.utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    @Autowired
    private StrategyFactory strategyFactory;

    @Autowired
    private Validation validation;

    public List<Product> getProduct(String searchProducts) {

        Strategy strategy = strategyFactory.getStrategy(searchProducts);

        List<Product> products = strategy.getProducts(searchProducts);

        if(validation.isPalindrome(searchProducts) &&
                searchProducts.length() >= strategy.getLengthSearchedProduct()) {
            products.stream()
                    .forEach(product -> getPriceWithDiscount(product));
        }

        return products;
    }

    private void getPriceWithDiscount(Product product) {
        product.setPriceBeforeDiscount(product.getPrice());
        product.setPrice(product.getPrice()/2);
    }
}
