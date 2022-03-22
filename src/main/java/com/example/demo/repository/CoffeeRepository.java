package com.example.demo.repository;

import com.example.demo.Entity.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee,Long> {


    Optional<Coffee> findById(String id);
}
