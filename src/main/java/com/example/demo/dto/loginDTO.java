package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class loginDTO {


    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String id;
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;
    private String address;
    private int phone;
    private String auth;
}
