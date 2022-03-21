package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class noticeDTO {
    private Long num;
    private String id;
    private String password;
    private String subject;
    private String content;
    private String regdate;
    private int count;
}
