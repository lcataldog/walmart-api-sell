package com.walmart.api.sell.strategies;

import com.walmart.api.sell.model.Product;

import java.util.List;

public interface Strategy {

    List<Product> getProducts(String searchProducts);

    int getLengthSearchedProduct();
}
