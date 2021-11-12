package com.centric.product.controller;

import com.centric.product.dao.ProductDAO;
import com.centric.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RestController
public class ProductController {


    @Autowired
    private ProductDAO productDAO;

    @PostMapping("/v1/products/save")
    @Transactional
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {

        try {
            String tags = String.join(",", product.getTags());
            product.setTagArray(tags);
            product.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        productDAO.save(product);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }


    @GetMapping("/v1/products/search/{category}")
    public ResponseEntity<List<Product>> searchByCategory(@PathVariable String category) {

        List<Product> products;
        try {

            products = productDAO.findByCategory(category);

            products.forEach(product -> {
                List<String> tags = Arrays.asList(product.getTagArray().split(","));
                product.setTags(tags);
            });

            products.sort(Comparator.comparing(Product::getCreatedAt).reversed());

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(products, HttpStatus.OK);
    }


}
