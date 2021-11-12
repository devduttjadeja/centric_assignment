package com.centric.product;

import com.centric.product.controller.ProductController;
import com.centric.product.dao.ProductDAO;
import com.centric.product.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductApplicationTests {


    @Autowired
    private ProductController productController;

    @Autowired
    private ProductDAO productDAO;

    private Product p1;
    private Product p2;
    private Product p3;


    @Before
    public void setup() {

        p1 = new Product();
        p1.setName("p1");
        p1.setDescription("p1_desc");
        p1.setBrand("p1_brand");
        p1.setTags(Arrays.asList("tag1", "tag2"));
        p1.setCategory("apparel");

        p2 = new Product();
        p2.setName("p2");
        p2.setDescription("p2_desc");
        p2.setBrand("p2_brand");
        p2.setTags(Arrays.asList("tag3", "tag4"));
        p2.setCategory("apparel");

        p3 = new Product();
        p3.setName("p3");
        p3.setDescription("p3_desc");
        p3.setBrand("p3_brand");
        p3.setTags(Arrays.asList("tag5", "tag6"));
        p3.setCategory("footwear");

        productDAO.deleteAll();

    }

    @Test
    public void test1SaveProduct() {
        long before = productDAO.count();
        productController.saveProduct(p1);
        productController.saveProduct(p2);
        productController.saveProduct(p3);
        long after = productDAO.count();
        Assert.assertEquals(before + 3, after);
    }

    @Test
    public void test2SearchByCategory() {
        productController.saveProduct(p1);
        productController.saveProduct(p2);
        productController.saveProduct(p3);
        long apparelCount = productDAO.findByCategory("apparel").size();
        Assert.assertEquals(apparelCount, 2);
    }


}
