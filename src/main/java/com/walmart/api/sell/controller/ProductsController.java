package com.walmart.api.sell.controller;

import com.walmart.api.sell.model.Product;
import com.walmart.api.sell.service.ProductsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class ProductsController {

    private final static Logger log = LoggerFactory.getLogger(ProductsController.class);

    @Autowired
    private ProductsService productService;

    /**
     *
     * @param searchProduct search product
     * @return products
     */
    @RequestMapping(value = "/products/{searchProduct}", method = RequestMethod.GET)
    @ApiOperation(value = "Index.", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded."),
            @ApiResponse(code = 400, message = "Bad Request, params invalid."),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition which prevented it from fulfilling the request.", response = Exception.class)
    })
    @CrossOrigin
    @Transactional(timeout = 240)
    public List<Product> getProducts(@PathVariable String searchProduct) {
        return productService.getProduct(searchProduct);
    }


}
