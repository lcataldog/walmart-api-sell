package com.walmart.api.sell.controller;

import com.walmart.api.sell.model.Product;
import com.walmart.api.sell.service.ProductsService;
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
public class ProductsControllerTest {

    @InjectMocks
    private ProductsController productsController;

    @Mock
    private ProductsService productsService;

    private String brand;
    private String description;
    private String image;
    private int id;
    private int price;
    private int priceBeforeDiscount;
    private String searchProducts;

    @BeforeEach
    public void setup() {
        searchProducts = "TEST";

        brand = "brand";
        description = "description";
        id = 1;
        image = "image";
        price = 1500;
        priceBeforeDiscount = 0;
    }

    @Test
    public void ShouldReturnProductsWhenCallEndpointProducts() {

        when(productsService.getProduct(anyString()))
                .thenReturn(getProducts());

        List<Product> response = productsController.getProducts(searchProducts);

        assertAll("Product invalid",
                () -> assertEquals("brand", response.get(0).getBrand()),
                () -> assertEquals("description", response.get(0).getDescription()),
                () -> assertEquals(1, response.get(0).getId()),
                () -> assertEquals("image", response.get(0).getImage()),
                () -> assertEquals(1500, response.get(0).getPrice()));

    }

    private List<Product> getProducts() {
        List products = new ArrayList();
        products.add(getProduct());

        return products;
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
