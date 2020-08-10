package com.walmart.api.sell.controller;

import com.walmart.api.sell.dao.ProductRepository;
import com.walmart.api.sell.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("int")
@TestPropertySource(properties = {"majorVer = 1"})
public class ProductsControllerBeanIntegrationTest extends BaseRestIntegrationTest  {

    @Autowired
    private ProductsController controller;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private Product getProduct(int id, String brand, String description) {
        Product product = new Product();
        product.setId(id);
        product.setBrand(brand);
        product.setDescription(description);
        product.setImage("image");
        product.setPrice(1500);
        return product;
    }

    @Test
    public void shouldReturnListProductsWithDiscount() {
        mongoTemplate.save(getProduct(181,"brand", "description"));
        List<Product> productList = controller.getProducts("181");

        assertEquals(1, productList.size());
        assertEquals(750, productList.get(0).getPrice());
        assertEquals(1500, productList.get(0).getPriceBeforeDiscount());
    }

    @Test
    public void shouldReturnListProductsWithoutDiscount() {
        mongoTemplate.save(getProduct(182,"brand", "description"));
        List<Product> productList = controller.getProducts("182");

        assertEquals(1, productList.size());
        assertEquals(1500, productList.get(0).getPrice());
        assertEquals(0, productList.get(0).getPriceBeforeDiscount());
    }

    @Test
    public void shouldReturnListProductsWitDiscountWhenBrandIsPalindrom() {
        mongoTemplate.save(getProduct(183,"asasa", "description"));
        List<Product> productList = controller.getProducts("asasa");

        assertEquals(2, productList.size());
        assertEquals(750, productList.get(0).getPrice());
        assertEquals(1500, productList.get(0).getPriceBeforeDiscount());
    }

    @Test
    public void shouldReturnListProductsWitDiscountWhenDescriptionIsPalindrom() {
        mongoTemplate.save(getProduct(184,"asasazz", "asasa"));
        List<Product> productList = controller.getProducts("asasa");

        assertEquals(3, productList.size());
        assertEquals(750, productList.get(0).getPrice());
        assertEquals(1500, productList.get(0).getPriceBeforeDiscount());
    }

    @Test
    public void shouldReturnListProductsWithoutDiscountWhenBrandAndDescriptionIsNotPalindrom() {
        mongoTemplate.save(getProduct(185,"asasazz", "asasazz"));
        List<Product> productList = controller.getProducts("asasazz");

        assertEquals(1, productList.size());
        assertEquals(1500, productList.get(0).getPrice());
        assertEquals(0, productList.get(0).getPriceBeforeDiscount());
    }
}
