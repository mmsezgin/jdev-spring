package com.cybertek.controller;

import com.cybertek.entity.User;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class HomeController {

    final String URI = "https://jsonplaceholder.typicode.com/users";  // URI of the API data that we consume

    private RestTemplate restTemplate;

    public HomeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    // if sb write /api bring all users. Make sure to uncomment out the ...api line.
    @GetMapping
    public User[] readAllUsers(){
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(URI,User[].class);
        // getForEntity() method is returning array, so return type must be an array.

        return responseEntity.getBody();
    }
    @GetMapping(value = "/{id}")
    public Object readUser(@PathVariable("id") Integer id){
        String URL = URI + "/{id}";
        return restTemplate.getForObject(URL,Object.class,id);
    }

    @GetMapping("/test")
    public ResponseEntity<Object> consumePostsFromDummyApi(){

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("app-id","61c2e109f58bec8104ea759d");

        HttpEntity<String> entity = new HttpEntity<>(headers); // need to create this line to convert headers to http
        //exchange() is also one of the most used methods in RestTemplate.
        // exchange () is to send headers
        // Go to the below website and get app id and change it below
        ResponseEntity<Object> response = restTemplate.exchange("https://dummyapi.io/data/v1/user?limit=10", HttpMethod.GET,entity,Object.class);

        return response;

    }




}
