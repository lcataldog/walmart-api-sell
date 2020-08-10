package com.walmart.api.sell.dao;

import com.walmart.api.sell.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {

    List<Product> findByDescriptionLikeOrBrandLike(String description, String brand);
}
