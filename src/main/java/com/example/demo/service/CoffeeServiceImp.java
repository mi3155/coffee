package com.example.demo.service;

import com.example.demo.Entity.Coffee;
import com.example.demo.dto.loginDTO;
import com.example.demo.repository.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoffeeServiceImp {

    @Autowired
    private final CoffeeRepository Coffeerepo;

    public Coffee CoffeeSignup(loginDTO dto){

        Coffee coffee = new Coffee();
        coffee.setId(dto.getId());
        coffee.setPassword(dto.getPassword());
        coffee.setAddress(dto.getAddress());
        coffee.setPhone(dto.getPhone());

        Coffeerepo.save(coffee);

        return coffee;
    }

}
