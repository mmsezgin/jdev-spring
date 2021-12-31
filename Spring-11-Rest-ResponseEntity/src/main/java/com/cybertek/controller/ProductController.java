package com.cybertek.controller;


import com.cybertek.entity.Product;
import com.cybertek.entity.ResponseWrapper;
import com.cybertek.service.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// HttpHeaderCreation are done in three ways in below methods
@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Check same method in Spring-11-Rest-HttpVerbs-Preferred, doing same job, but this is the most preferred way
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id){
        return ResponseEntity.ok(productService.getProduct(id));
    }
    // Pay attention to import org.springframework.http NOT java.net.http
    @GetMapping
    public  ResponseEntity<List<Product>> getProducts(){

        // Making Http Header Creation using HttpHeaders
        HttpHeaders responseHttpHeaders = new HttpHeaders();
        responseHttpHeaders.set("Version","Cybertek.v1");
        responseHttpHeaders.set("Operation","Get List");
        // status, header and body need to be passed ResponseEntity
        //Most companies use ResponseEntity
        return ResponseEntity
                .ok()
                .headers(responseHttpHeaders)
                .body(productService.getProducts()); // gives error if forgot to change the return type to ResponseEntity<List<Product>>
    }

    @PostMapping
    public  ResponseEntity<List<Product>> createProduct(@RequestBody Product product){

        List<Product> set = productService.createProduct(product);
        // Check the method above, below same things done in different way than the method above
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Version","Cybertek.V1")
                .header("Operation","Create")
                .body(set);
    }

    @DeleteMapping(value = "/{id}")
    public  ResponseEntity<List<Product>> deleteProduct(@PathVariable("id") long id){

        HttpHeaders responseHttpHeaders = new HttpHeaders();
        responseHttpHeaders.set("Version","Cybertek.v1");
        responseHttpHeaders.set("Operation","Delete");

        List<Product> list = productService.delete(id);

       return new ResponseEntity<>(list,responseHttpHeaders,HttpStatus.OK);
    }
    // This method is created using MultiValueMap
    @PutMapping(value = "/{id}")
    public  ResponseEntity<List<Product>> updateProduct(@PathVariable("id") long id,@RequestBody Product product){

        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("Version","Cybertek.V1");
        map.add("Operation","Update");

        List<Product> list = productService.updateProduct(id,product);

        return new ResponseEntity<>(list,map,HttpStatus.OK);

    }

    @GetMapping("/read")
    public ResponseEntity<ResponseWrapper> readAllProducts(){
        return ResponseEntity
                .ok(new ResponseWrapper("products successfully retrieved",productService.getProducts()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseWrapper> deleteProduct2(@PathVariable("id") long id){
        productService.delete(id); //forgotten in the Ozzy's code.
        return ResponseEntity.ok(new ResponseWrapper("product successfully deleted"));
    }

    @DeleteMapping("/delete2/{id}")
    public ResponseEntity<ResponseWrapper> deleteProduct3(@PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseWrapper("product successfully deleted"));
    }

}














