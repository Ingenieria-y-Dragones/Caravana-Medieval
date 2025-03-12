package co.edu.javeriana.caravana.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.javeriana.caravana.model.Jugador;
import co.edu.javeriana.caravana.repository.CaravanaRepository;
import co.edu.javeriana.caravana.repository.CiudadRepository;
import co.edu.javeriana.caravana.repository.SistemaRepository;
import co.edu.javeriana.caravana.service.JugadorService;

@Controller
@RequestMapping("/jugador")
public class JugadorController {
    @Autowired
    private JugadorService jugadorService;
    
    @Autowired
    private CaravanaRepository caravanaRepo;
    @Autowired
    private CiudadRepository ciudadRepo;
    @Autowired
    private SistemaRepository sistemaRepo;

    @GetMapping("/list")
    public String listAll(@RequestParam(required = false) Jugador.TipoRol rol, Model model) {
        List<Jugador> jugadores = (rol == null) 
            ? jugadorService.findAll() 
            : jugadorService.findByRol(rol);
        
        model.addAttribute("jugadores", jugadores);
        model.addAttribute("roles", Jugador.TipoRol.values()); // Para el filtro
        return "jugador-list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("caravanas", caravanaRepo.findAll());
        model.addAttribute("ciudades", ciudadRepo.findAll());
        model.addAttribute("sistemas", sistemaRepo.findAll());
        model.addAttribute("roles", Jugador.TipoRol.values());
        return "jugador-form";
    }

    @PostMapping("/save")
    public String save(
        @RequestParam String nombre,
        @RequestParam Long tiempoJugado,
        @RequestParam Jugador.TipoRol rol,
        @RequestParam(required = false) Long caravanaId,
        @RequestParam(required = false) Long ciudadId,
        @RequestParam(required = false) Long sistemaId
    ) {
        jugadorService.createJugador(nombre, tiempoJugado, rol, caravanaId, ciudadId, sistemaId);
        return "redirect:/jugador/list";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        Jugador jugador = jugadorService.findById(id);
        model.addAttribute("jugador", jugador);
        return "jugador-view";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Jugador jugador = jugadorService.findById(id);
        model.addAttribute("jugador", jugador);
        model.addAttribute("caravanas", caravanaRepo.findAll());
        model.addAttribute("ciudades", ciudadRepo.findAll());
        model.addAttribute("sistemas", sistemaRepo.findAll());
        model.addAttribute("roles", Jugador.TipoRol.values());
        return "jugador-form";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Jugador jugador) {
        jugadorService.update(jugador.getId(), jugador);
        return "redirect:/jugador/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        jugadorService.deleteById(id);
        return "redirect:/jugador/list";
    }
}