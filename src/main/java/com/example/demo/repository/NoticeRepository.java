package com.example.demo.repository;

import com.example.demo.Entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice,Long> {

}

