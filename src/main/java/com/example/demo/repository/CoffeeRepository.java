package com.example.demo.repository;

import com.example.demo.Entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoffeeRepository extends JpaRepository<Coffee,Long> {

    Optional<Coffee> findById(String id);

}
