package com.walmart.api.sell.strategy;

import com.walmart.api.sell.dao.ProductRepository;
import com.walmart.api.sell.model.Product;
import com.walmart.api.sell.strategies.StrategyOperationById;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StrategyOperationByIdTest {

    @InjectMocks
    private StrategyOperationById strategyOperationById;

    @Mock
    private ProductRepository repository;

    @Test
    public void ShouldReturnListProductsWhenCallGetProducts() {
        String searchProducts = "181";
        Product product = new Product();
        List<Product> products = new ArrayList<>();
        products.add(product);

        when(repository.findById(anyInt())).thenReturn(Optional.of(product));

        List<Product> productList = strategyOperationById.getProducts(searchProducts);

        assertEquals(1, productList.size());
    }

    @Test
    public void ShouldReturnListProductsEmptyWhenCallGetProductsAndNotFoundProducts() {
        String searchProducts = "182";

        when(repository.findById(anyInt())).thenReturn(Optional.empty());

        List<Product> productList = strategyOperationById.getProducts(searchProducts);

        assertEquals(0, productList.size());
    }

    @Test
    public void ShouldReturnLengthWhenCallGetLengthSearchedProduct() {
        ReflectionTestUtils.setField(strategyOperationById, "lengthSearchedProduct", 3);
        assertEquals(3, strategyOperationById.getLengthSearchedProduct());
    }
}
