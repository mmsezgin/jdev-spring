package com.cybertek.controller;


import com.cybertek.entity.Product;
import com.cybertek.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//When we put @RestController we do NOT need @ResponseBody anymore.
@RestController
@RequestMapping("/products") // Newly added. class level @RequestMapping so change endpoints respectively
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // @ResponseBody removed from return types in below methods
    // @RequestMapping + PUT, POST, DELETE methods are changed with @GetMapping @PostMapping @DeleteMapping @PutMapping
    @GetMapping(value = "/{id}")
    public Product getProduct(@PathVariable("id") long id){

        return productService.getProduct(id);
    }

    @GetMapping
    public List<Product> getProducts(){

        return productService.getProducts();
    }

    @PostMapping
    public  List<Product> createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @DeleteMapping(value = "/{id}")
    public  List<Product> deleteProduct(@PathVariable("id") long id){
        return productService.delete(id);
    }

    @PutMapping(value = "/{id}")
    public  List<Product> updateProduct(@PathVariable("id") long id,@RequestBody Product product){
        return productService.updateProduct(id, product);
    }

}














