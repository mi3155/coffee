package com.example.demo.controller;

import com.example.demo.Entity.Coffee;
import com.example.demo.dto.loginDTO;
import com.example.demo.service.CoffeeServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class commonController {

    @Autowired
    final private CoffeeServiceImp CoffeeService;



    @GetMapping("/coffee")
    public String test1()
    {
        return "index.html";

    }

    @GetMapping("/Signup")
    public String test2(){
        return "Signup.html";
    }

    @PostMapping("/SignupProc")
    public String test3(loginDTO dto){
        System.out.println(dto.toString());
        CoffeeService.save(dto);
        System.out.println("id : " + dto.getId() + "님 가입을 환영합니다.");

        return "index.html";
    }

    @GetMapping("/loginfailed")
    public String text4(){
        return "loginfailed.html";
    }

    @GetMapping("test")
    public String text5(){
        return "text.html";
    }

}
