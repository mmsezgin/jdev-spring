package com.cybertek.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String index(){

        return "/index";
    }

    @GetMapping(value = {"/login","/"}) //either localhost or localhost:8080 take me to login page
    public String login(){
        return "login";
    }
}
