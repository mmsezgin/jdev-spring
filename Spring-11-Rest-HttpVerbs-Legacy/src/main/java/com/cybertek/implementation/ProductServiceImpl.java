package com.cybertek.implementation;

import com.cybertek.entity.Product;
import com.cybertek.repository.ProductRepository;
import com.cybertek.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {   //

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }  // find all products and retrieve

    @Override
    public List<Product> delete(long id) {
        productRepository.deleteById(id); // delete by id
        return productRepository.findAll(); // return new list without deleted one
    }

    @Override
    public List<Product> updateProduct(long id, Product product) {
        Product obj = productRepository.findById(id).get(); //find the product by id. Why using get? findById is returning Optional
        obj.setName(product.getName()); //
        productRepository.save(obj);
        return productRepository.findAll(); // return all updated ones
    }

    @Override
    public List<Product> createProduct(Product product) {
        productRepository.save(product);
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(long id) {
        return productRepository.findById(id).get();
    }
}
