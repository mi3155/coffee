package com.example.demo.controller;

import com.example.demo.Entity.Coffee;
import com.example.demo.dto.loginDTO;
import com.example.demo.service.CoffeeServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


    @GetMapping("/logincoffee")
    public String loginmain(Authentication authentication, Model model){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("login", userDetails);

        return "logincoffee.html";
    }

    //로그아웃
    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/coffee";
    }
}
