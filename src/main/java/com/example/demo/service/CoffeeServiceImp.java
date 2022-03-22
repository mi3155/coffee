package com.example.demo.service;

import com.example.demo.Entity.Coffee;
import com.example.demo.dto.loginDTO;
import com.example.demo.repository.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoffeeServiceImp implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        //이과정을 통해 입력된 id즉 이메일,패스워드와 저장된 이메일,패스워드을 비교
        Optional<Coffee> result = Coffeerepo.findById(id);
        System.out.println("result = " + result.toString());
        if (result.isPresent())  // result에 값이 있다면
            return result.get(); // entity의 동일 id의 정보들을 UserDetails형태로 리턴
        else
            throw new UsernameNotFoundException("Check Email");

    }



    public Coffee save(loginDTO dto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        dto.setPassword(dto.getPassword());
        Coffee coffee = new Coffee();
        coffee.setId(dto.getId());
        coffee.setPassword(encoder.encode(dto.getPassword()));
        coffee.setAddress(dto.getAddress());
        coffee.setPhone(dto.getPhone());
        coffee.setAuth(dto.getAuth());

        Coffeerepo.save(coffee);

        return coffee;
    }


        public void ValidateDuplicateMember(loginDTO dto) {
            Optional<Coffee> findMembers = Coffeerepo.findById(dto.getId());
            if (!findMembers.isEmpty()) {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            }
        }


}
