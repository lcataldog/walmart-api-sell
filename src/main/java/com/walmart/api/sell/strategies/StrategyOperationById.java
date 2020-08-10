package com.walmart.api.sell.strategies;

import com.walmart.api.sell.dao.ProductRepository;
import com.walmart.api.sell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StrategyOperationById implements Strategy {

    @Autowired
    private ProductRepository repository;

    @Value("${strategyOperationById.lengthSearchedProduct}")
    private int lengthSearchedProduct;

    @Override
    public List<Product> getProducts(String searchProducts) {
        List<Product> productList = new ArrayList<>();
        Optional<Product> product = repository.findById(Integer.parseInt(searchProducts));

        if (product.isPresent()) {
            productList.add(product.get());
        }

        return productList;
    }

    @Override
    public int getLengthSearchedProduct(){
        return lengthSearchedProduct;
    };
}
