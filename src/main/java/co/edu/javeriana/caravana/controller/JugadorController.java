package co.edu.javeriana.caravana.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.javeriana.caravana.model.Caravanero;
import co.edu.javeriana.caravana.model.Comerciante;
import co.edu.javeriana.caravana.model.Jugador;
import co.edu.javeriana.caravana.repository.CaravanaRepository;
import co.edu.javeriana.caravana.repository.CiudadRepository;
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

    // Formulario de creación con opciones de tipo
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("caravanas", caravanaRepo.findAll());
        model.addAttribute("ciudades", ciudadRepo.findAll());
        return "jugador-form";
    }

    // Guardar con manejo de relaciones
    @PostMapping("/save")
    public String save(@RequestParam String tipo,
                      @RequestParam String nombre,
                      @RequestParam Long tiempoJugado,
                      @RequestParam(required = false) Long caravanaId,
                      @RequestParam(required = false) Long ciudadId) {
        
        jugadorService.createJugador(tipo, nombre, tiempoJugado, caravanaId, ciudadId);
        return "redirect:/jugador/list";
    }

    // Listar con filtro por tipo
    @GetMapping("/list")
    public String listAll(@RequestParam(required = false) String tipo, Model model) {
        List<Jugador> jugadores = (tipo == null) ? jugadorService.findAll() : 
            switch(tipo) {
                case "caravanero" -> jugadorService.findAllCaravaneros();
                case "comerciante" -> jugadorService.findAllComerciantes();
                default -> throw new IllegalArgumentException("Tipo inválido");
            };
        model.addAttribute("jugadores", jugadores);
        return "jugador-list";
    }

    // Detalle mostrando relaciones específicas
    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        Jugador jugador = jugadorService.findById(id);
        model.addAttribute("jugador", jugador);
        model.addAttribute("esCaravanero", jugador instanceof Caravanero);
        model.addAttribute("esComerciante", jugador instanceof Comerciante);
        return "jugador-view";
    }
}