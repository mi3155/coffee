package com.example.demo.security;

import com.example.demo.service.CoffeeServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // 웹에서 사용하는 설정파일
@RequiredArgsConstructor
public class config extends WebSecurityConfigurerAdapter {

    @Autowired
    private final CoffeeServiceImp service;

    @Bean
    PasswordEncoder passwordencoder(){

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(passwordencoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/coffee").permitAll()
                .antMatchers("/test").hasRole("ADMIN");

                //.antMatchers("/list.do").hasRole("USER")  아직설정 x
                //.antMatchers("/post.do").hasRole("ADMIN"); 아직설정 x
        http.formLogin()
                .loginPage("/coffee") // 아직 설정 x
                .defaultSuccessUrl("/coffee"); //로그인 성공시 기본적으로 나올 페이지 // 아직 설정 x
        http.logout()
                .logoutSuccessUrl("/coffee")
                .invalidateHttpSession(true);
        http.csrf().disable();
                //접근이 실패했을 경우
                // .exceptionHandling().accessDeniedPage("/loginfailed");

    }
}
