package com.centric.product.dao;

import com.centric.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, String> {
    List<Product> findByCategory(String category);
}
