package com.example.proyecto.controller;

import com.example.proyecto.model.Barbero;
import com.example.proyecto.repository.BarberoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/barberos")
public class BarberoController {

    private final BarberoRepository repository;

    public BarberoController(BarberoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("barberos", repository.findAll());
        return "barberos";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("barbero", new Barbero());
        return "form_barbero";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Barbero barbero) {
        repository.save(barbero);
        return "redirect:/barberos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/barberos";
    }
}