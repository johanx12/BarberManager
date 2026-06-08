package com.example.proyecto.controller;

import com.example.proyecto.repository.BarberoRepository;
import com.example.proyecto.repository.CitaRepository;
import com.example.proyecto.repository.ClienteRepository;
import com.example.proyecto.repository.ServicioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final ClienteRepository clienteRepository;
    private final BarberoRepository barberoRepository;
    private final ServicioRepository servicioRepository;
    private final CitaRepository citaRepository;

    public DashboardController(ClienteRepository clienteRepository,
                                BarberoRepository barberoRepository,
                                ServicioRepository servicioRepository,
                                CitaRepository citaRepository) {
        this.clienteRepository = clienteRepository;
        this.barberoRepository = barberoRepository;
        this.servicioRepository = servicioRepository;
        this.citaRepository = citaRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication auth) {
        boolean isAdmin = auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("username", auth.getName());
        model.addAttribute("totalClientes", clienteRepository.count());
        model.addAttribute("totalBarberos", barberoRepository.count());
        model.addAttribute("totalServicios", servicioRepository.count());
        model.addAttribute("totalCitas", citaRepository.count());
        return "dashboard";
    }
}
