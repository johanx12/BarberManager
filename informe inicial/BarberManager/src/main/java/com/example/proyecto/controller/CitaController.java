
package com.example.proyecto.controller;

import com.example.proyecto.model.*;
import com.example.proyecto.repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/citas")
public class CitaController {
    private final CitaRepository citaRepository;
    private final ClienteRepository clienteRepository;
    private final BarberoRepository barberoRepository;
    private final ServicioRepository servicioRepository;

    public CitaController(CitaRepository citaRepository, ClienteRepository clienteRepository,
    BarberoRepository barberoRepository, ServicioRepository servicioRepository){
        this.citaRepository=citaRepository;
        this.clienteRepository=clienteRepository;
        this.barberoRepository=barberoRepository;
        this.servicioRepository=servicioRepository;
    }

    @GetMapping
    public String listar(Model model, Authentication auth){
        boolean isAdmin=auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        if(isAdmin){
            model.addAttribute("citas", citaRepository.findAll());
        }else{
            var cliente=clienteRepository.findByUsername(auth.getName());
            model.addAttribute("citas", cliente.map(citaRepository::findByCliente).orElse(java.util.Collections.emptyList()));
        }
        model.addAttribute("isAdmin", isAdmin);
        return "citas";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model, Authentication auth){
        boolean isAdmin=auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        model.addAttribute("cita", new Cita());
        if(isAdmin) model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("barberos", barberoRepository.findAll());
        model.addAttribute("servicios", servicioRepository.findAll());
        model.addAttribute("isAdmin", isAdmin);
        return "form_cita";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam("fechaHora") String fechaHora,
    @RequestParam(value="clienteId",required=false) Long clienteId,
    @RequestParam("barberoId") Long barberoId,
    @RequestParam("servicioId") Long servicioId,
    Authentication auth, RedirectAttributes ra){
      try{
        boolean isAdmin=auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        Cliente cliente;
        if(isAdmin){
          cliente=clienteRepository.findById(clienteId).orElseThrow();
        }else{
          cliente=clienteRepository.findByUsername(auth.getName())
             .orElseGet(()->{
                Cliente c=new Cliente();
                c.setNombre("Cliente");
                c.setUsername(auth.getName());
                c.setCorreo(auth.getName()+"@local.com");
                c.setTelefono("000000");
                return clienteRepository.save(c);
             });
        }
        Cita cita=new Cita();
        cita.setFechaHora(java.time.LocalDateTime.parse(fechaHora));
        cita.setCliente(cliente);
        cita.setBarbero(barberoRepository.findById(barberoId).orElseThrow());
        cita.setServicio(servicioRepository.findById(servicioId).orElseThrow());
        citaRepository.save(cita);
        ra.addFlashAttribute("mensaje","Cita registrada exitosamente");
      }catch(Exception e){
        ra.addFlashAttribute("error","Error al guardar la cita: "+e.getMessage());
        return "redirect:/citas/nuevo";
      }
      return "redirect:/citas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, Authentication auth, RedirectAttributes ra){
      boolean isAdmin=auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
      if(!isAdmin){ ra.addFlashAttribute("error","No tienes permisos"); return "redirect:/citas";}
      citaRepository.deleteById(id);
      return "redirect:/citas";
    }
}
