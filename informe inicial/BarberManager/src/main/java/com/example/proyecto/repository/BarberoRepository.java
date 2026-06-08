package com.example.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proyecto.model.Barbero;

public interface BarberoRepository extends JpaRepository<Barbero, Long> {
}