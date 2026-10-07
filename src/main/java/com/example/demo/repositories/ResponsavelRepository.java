package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Responsavel;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
    
}
