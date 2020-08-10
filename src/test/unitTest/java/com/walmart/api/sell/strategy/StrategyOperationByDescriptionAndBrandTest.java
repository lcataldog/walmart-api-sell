package com.walmart.api.sell.strategy;

import com.walmart.api.sell.dao.ProductRepository;
import com.walmart.api.sell.model.Product;
import com.walmart.api.sell.strategies.StrategyOperationByDescriptionAndBrand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StrategyOperationByDescriptionAndBrandTest {

    @InjectMocks
    private StrategyOperationByDescriptionAndBrand strategyOperationByDescriptionAndBrand;

    @Mock
    private ProductRepository repository;

    @Test
    public void ShouldReturnListProductsWhenCallGetProducts() {
        String searchProducts = "Search";
        Product product = new Product();
        List<Product> products = new ArrayList<>();
        products.add(product);

        when(repository.findByDescriptionLikeOrBrandLike(anyString(),anyString())).thenReturn(products);

        List<Product> productList = strategyOperationByDescriptionAndBrand.getProducts(searchProducts);

        assertEquals(1, productList.size());
    }

    @Test
    public void ShouldReturnLengthWhenCallGetLengthSearchedProduct() {
        ReflectionTestUtils.setField(strategyOperationByDescriptionAndBrand, "lengthSearchedProduct", 4);
        assertEquals(4, strategyOperationByDescriptionAndBrand.getLengthSearchedProduct());
    }

}
