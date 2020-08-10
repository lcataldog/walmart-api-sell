package com.walmart.api.sell.service;

import com.walmart.api.sell.model.Product;
import com.walmart.api.sell.strategies.StrategyFactory;
import com.walmart.api.sell.strategies.StrategyOperationByDescriptionAndBrand;
import com.walmart.api.sell.strategies.StrategyOperationById;
import com.walmart.api.sell.utils.Validation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductsServiceTest {

    @InjectMocks
    private ProductsService productsService;

    @Mock
    private StrategyFactory strategyFactory;

    @Mock
    private Validation validation;

    @Mock
    private StrategyOperationById strategyOperationById;

    @Mock
    private StrategyOperationByDescriptionAndBrand strategyOperationByDescriptionAndBrand;

    private List<Product> products = new ArrayList<>();
    private String brand;
    private String description;
    private String image;
    private int id;
    private int price;
    private int priceBeforeDiscount;
    private String searchProducts;

    @BeforeEach
    public void setup() {

        brand = "brand";
        description = "description";
        id = 1;
        image = "image";
        price = 1500;
        priceBeforeDiscount = 0;

        products.add(getProduct());

        when(validation.isPalindrome(anyString())).thenReturn(true);
        when(strategyFactory.getStrategy(anyString())).thenReturn(strategyOperationByDescriptionAndBrand);
    }

    @Test
    public void ShouldReturnListProductsWithoutDiscountWhenSearchProductByDescriptionAndBrand() {
        searchProducts = "ABA";
        when(strategyOperationByDescriptionAndBrand.getProducts(anyString())).thenReturn(products);
        when(strategyOperationByDescriptionAndBrand.getLengthSearchedProduct()).thenReturn(4);

        List<Product> productList = productsService.getProduct(searchProducts);

        assertAll("Product invalid",
                () -> assertEquals("brand", productList.get(0).getBrand()),
                () -> assertEquals("description", productList.get(0).getDescription()),
                () -> assertEquals(1, productList.get(0).getId()),
                () -> assertEquals("image", productList.get(0).getImage()),
                () -> assertEquals(1500, productList.get(0).getPrice()),
                () -> assertEquals(0, productList.get(0).getPriceBeforeDiscount())
        );
    }

    @Test
    public void ShouldReturnListProductsWithDiscountWhenSearchProductByDescriptionAndBrand() {
        searchProducts = "ABBA";
        when(strategyOperationByDescriptionAndBrand.getProducts(anyString())).thenReturn(products);
        when(strategyOperationByDescriptionAndBrand.getLengthSearchedProduct()).thenReturn(4);

        List<Product> productList = productsService.getProduct(searchProducts);

        assertAll("Product invalid",
                () -> assertEquals("brand", productList.get(0).getBrand()),
                () -> assertEquals("description", productList.get(0).getDescription()),
                () -> assertEquals(1, productList.get(0).getId()),
                () -> assertEquals("image", productList.get(0).getImage()),
                () -> assertEquals(750, productList.get(0).getPrice()),
                () -> assertEquals(1500, productList.get(0).getPriceBeforeDiscount()));
    }

    @Test
    public void ShouldReturnListProductsWithDiscountWhenSearchProductById() {
        searchProducts = "181";
        when(strategyFactory.getStrategy(anyString())).thenReturn(strategyOperationById);
        when(strategyOperationById.getProducts(anyString())).thenReturn(products);
        when(strategyOperationById.getLengthSearchedProduct()).thenReturn(3);

        List<Product> productList = productsService.getProduct(searchProducts);

        assertAll("Product invalid",
                () -> assertEquals("brand", productList.get(0).getBrand()),
                () -> assertEquals("description", productList.get(0).getDescription()),
                () -> assertEquals(1, productList.get(0).getId()),
                () -> assertEquals("image", productList.get(0).getImage()),
                () -> assertEquals(750, productList.get(0).getPrice()),
                () -> assertEquals(1500, productList.get(0).getPriceBeforeDiscount()));
    }

    @Test
    public void ShouldReturnListProductsWithoutDiscountWhenSearchProductById() {
        searchProducts = "182";
        when(strategyFactory.getStrategy(anyString())).thenReturn(strategyOperationById);
        when(strategyOperationById.getProducts(anyString())).thenReturn(products);
        when(validation.isPalindrome(anyString())).thenReturn(false);

        List<Product> productList = productsService.getProduct(searchProducts);

        assertAll("Product invalid",
                () -> assertEquals("brand", productList.get(0).getBrand()),
                () -> assertEquals("description", productList.get(0).getDescription()),
                () -> assertEquals(1, productList.get(0).getId()),
                () -> assertEquals("image", productList.get(0).getImage()),
                () -> assertEquals(1500, productList.get(0).getPrice()),
                () -> assertEquals(0, productList.get(0).getPriceBeforeDiscount())
        );
    }

    @Test
    public void ShouldReturnListProductsEmptyWhenSearchProduct() {
        searchProducts = "181";
        when(strategyFactory.getStrategy(anyString())).thenReturn(strategyOperationById);
        when(strategyOperationById.getProducts(anyString())).thenReturn(new ArrayList<>());
        when(strategyOperationById.getLengthSearchedProduct()).thenReturn(3);
        when(validation.isPalindrome(anyString())).thenReturn(true);

        List<Product> productList = productsService.getProduct(searchProducts);

        assertEquals(0, productList.size());
    }

    private Product getProduct() {
        Product product = new Product();
        product.setPrice(price);
        product.setBrand(brand);
        product.setPriceBeforeDiscount(priceBeforeDiscount);
        product.setDescription(description);
        product.setId(id);
        product.setImage(image);
        return product;
    }
}
