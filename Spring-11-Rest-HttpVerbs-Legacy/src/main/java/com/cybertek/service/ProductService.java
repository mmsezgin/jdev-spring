package com.cybertek.service;

import com.cybertek.entity.Product;

import java.util.List;

public interface ProductService {


    // void as a return type can be used. But List<Product> is developer's best practice.
    List<Product> getProducts();
    // After deletion, it should have an updated list
    List<Product> delete(long id);
    List<Product> updateProduct(long id,Product product);
    List<Product> createProduct(Product product);
    Product getProduct(long id);

}
