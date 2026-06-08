package com.example.proyecto.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.proyecto.model.Cita;
import com.example.proyecto.model.Cliente;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByCliente(Cliente cliente);
}
