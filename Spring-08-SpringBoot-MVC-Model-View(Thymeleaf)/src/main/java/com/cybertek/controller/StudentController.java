package com.cybertek.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/welcome")
    public String homePage(Model model){

       model.addAttribute("name","Cybertek");
       model.addAttribute("course","MVC");

       String subject = "Collections";
       model.addAttribute("subject",subject);

       //create some random studentid (0-1000) and show it in your UI


       return "student/welcome";
    }
}
