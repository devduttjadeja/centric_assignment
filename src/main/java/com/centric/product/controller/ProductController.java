package com.centric.product.controller;

import com.centric.product.dao.ProductDAO;
import com.centric.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

@RestController
public class ProductController {


    @Autowired
    private ProductDAO productDAO;

    @PostMapping("/saveProduct")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {

        String tags = String.join(",", product.getTags());
        product.setTagArray(tags);

        /*
        1. LocalDateTime.now() -> 2021-11-10T21:44:35.743560500
        2. LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS) -> 2021-11-10T21:45:13
        3. OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS) -> 2021-11-10T21:46:07-05:00
        4. OffsetDateTime.now(ZoneOffset.UTC).truncatedTo(ChronoUnit.SECONDS) -> 2021-11-11T02:35:07Z

        Using 4. as it matches the desciprion in the document
        */
        product.setCreated_at(OffsetDateTime.now(ZoneOffset.UTC).truncatedTo(ChronoUnit.SECONDS));

        productDAO.save(product);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }


}
