package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO {
    private int nowPage;
    private int nowBlock;
    private int pageStart;
    private int pageEnd;
}
