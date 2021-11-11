package com.centric.product;

import com.centric.product.controller.ProductController;
import com.centric.product.dao.ProductDAO;
import com.centric.product.model.Product;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductApplicationTests {


    @Autowired
    private ProductController productController;

    @Autowired
    private ProductDAO productDAO;

    @Test
    void contextLoads() {
    }

    @Test
    void testSaveProduct() {

        long before = productDAO.count();

        Product product = new Product();
        product.setName("test");
        product.setDescription("test_desc");
        product.setBrand("test_brand");
        product.setTags(Arrays.asList("tag1", "tag2"));
        product.setCategory("apparel");
        productController.saveProduct(product);

        long after = productDAO.count();

        Assert.assertEquals(before + 1, after);

    }

}
