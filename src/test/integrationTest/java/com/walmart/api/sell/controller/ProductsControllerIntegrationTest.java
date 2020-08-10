package com.walmart.api.sell.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.is;

@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = {"majorVer = 1"})
@ActiveProfiles("dev")
public class ProductsControllerIntegrationTest extends BaseRestIntegrationTest {

    private static final String RESOURCE_PATH = "/products/181";
    private static final String RESOURCE_BAD_PATH = "/products/";

    @Test
    public void shouldReturnHttpStatus200WhenNoWantedProductsFound() {
        requestSpecification
                .when()
                .get(RESOURCE_PATH)
                .then()
                .assertThat()
                .statusCode(is(HttpStatus.OK.value()));
    }

    @Test
    public void shouldReturnHttpStatus404WhenUrlIsNotExiste() {
        requestSpecification
                .when()
                .get(RESOURCE_BAD_PATH)
                .then()
                .assertThat()
                .statusCode(is(HttpStatus.NOT_FOUND.value()));
    }

}
