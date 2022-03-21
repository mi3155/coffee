package com.example.demo.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name="tbl_notice")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notice {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long num;

    @Column
    private String id;

    @Column
    private String password;

    @Column
    private String subject;

    @Column
    private String content;

    @Column(columnDefinition = "datetime")
    private String regdate;

    @Column
    private int count;
}
