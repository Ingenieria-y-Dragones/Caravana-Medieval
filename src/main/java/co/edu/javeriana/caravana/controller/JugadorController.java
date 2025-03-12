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

import co.edu.javeriana.caravana.model.Administrador;
import co.edu.javeriana.caravana.model.Caravanero;
import co.edu.javeriana.caravana.model.Comerciante;
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
    public String listAll(@RequestParam(required = false) String tipo, Model model) {
        List<Jugador> jugadores;
        
        if (tipo == null) {
            jugadores = jugadorService.findAll();
        } else {
            jugadores = switch (tipo.toLowerCase()) {
                case "caravanero" -> jugadorService.findAllCaravaneros();
                case "comerciante" -> jugadorService.findAllComerciantes();
                case "administrador" -> jugadorService.findAllAdministradores();
                default -> jugadorService.findAll();
            };
        }
        
        model.addAttribute("jugadores", jugadores);
        return "jugador-list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("caravanas", caravanaRepo.findAll());
        model.addAttribute("ciudades", ciudadRepo.findAll());
        model.addAttribute("sistemas", sistemaRepo.findAll());
        return "jugador-form";
    }

    @PostMapping("/save")
    public String save(
        @RequestParam String tipo,
        @RequestParam String nombre,
        @RequestParam Long tiempoJugado,
        @RequestParam(required = false) Long caravanaId,
        @RequestParam(required = false) Long ciudadId,
        @RequestParam(required = false) Long sistemaId
    ) {
        jugadorService.createJugador(tipo, nombre, tiempoJugado, caravanaId, ciudadId, sistemaId);
        return "redirect:/jugador/list";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        Jugador jugador = jugadorService.findById(id);
        model.addAttribute("jugador", jugador);
        model.addAttribute("esCaravanero", jugador instanceof Caravanero);
        model.addAttribute("esComerciante", jugador instanceof Comerciante);
        model.addAttribute("esAdministrador", jugador instanceof Administrador);
        return "jugador-view";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Jugador jugador = jugadorService.findById(id);
        model.addAttribute("jugador", jugador);
        model.addAttribute("caravanas", caravanaRepo.findAll());
        model.addAttribute("ciudades", ciudadRepo.findAll());
        model.addAttribute("sistemas", sistemaRepo.findAll());
        return "jugador-form";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Jugador jugador) {
        jugadorService.update(jugador.getId(), jugador);
        return "redirect:/jugador/list";
    }

    /*@GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        jugadorService.delete(id);
        return "redirect:/jugador/list";
    }*/
}