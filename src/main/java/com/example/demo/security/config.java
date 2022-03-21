package com.example.demo.security;

import com.example.demo.service.CoffeeServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration // 웹에서 사용하는 설정파일
@EnableWebSecurity
@RequiredArgsConstructor
public class config extends WebSecurityConfigurerAdapter {

    @Autowired
    private final CoffeeServiceImp service;

    //실패시 핸들러 의존성 주입
 //   private final AuthenticationFailureHandler customFailurHandler;

    @Bean
    PasswordEncoder passwordencoder(){

        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(passwordencoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/coffee").permitAll()
                .antMatchers("/notice").hasRole("ADMIN");

                //.antMatchers("/list.do").hasRole("USER")  아직설정 x
                //.antMatchers("/post.do").hasRole("ADMIN"); 아직설정 x
        //        .anyRequest().authenticated();
        http.formLogin()
                .loginPage("/coffee") // 아직 설정 x
                .defaultSuccessUrl("/logincoffee"); //로그인 성공시 기본적으로 나올 페이지
        http.logout()
                .logoutSuccessUrl("/coffee")
                .invalidateHttpSession(true);
        //http.exceptionHandling()
        //                .accessDeniedPage("/"); // 인증되지 않은 사용자에게 제공할 url
        http.csrf().disable();

    }
}
