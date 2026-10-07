package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Crianca;

public interface CriancaRepository extends JpaRepository<Crianca, Long> {
    
}
