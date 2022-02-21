package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class commonController {

    @GetMapping("coffee")
    public String test1(){
        return "index.html";
    }
}
