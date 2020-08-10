package com.walmart.api.sell.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Document(collection = "products")
@NoArgsConstructor
@Data
public class Product implements Serializable {

    @Field("id")
    private int id;

    @Field("brand")
    private String brand;

    @Field("description")
    private String description;

    private String image;
    private int price;

    private int priceBeforeDiscount=0;

}
