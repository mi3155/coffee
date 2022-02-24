package com.example.demo.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@Data
@Table(name="tbl_coffee")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    @Column(unique = true)
    private String id;

    @Column
    private String password;

    @Column
    private String address;

    @Column
    private int phone;


}
