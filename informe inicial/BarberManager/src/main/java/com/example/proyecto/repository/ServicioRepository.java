package com.example.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proyecto.model.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
}