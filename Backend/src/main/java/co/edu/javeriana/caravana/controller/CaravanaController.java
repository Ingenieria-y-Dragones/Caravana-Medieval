package co.edu.javeriana.caravana.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana.dto.CaravanaDTO;
import co.edu.javeriana.caravana.dto.JugadorDTO;
import co.edu.javeriana.caravana.service.CaravanaService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/caravana")
public class CaravanaController {
    @Autowired
    private CaravanaService caravanaService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("{idCaravana}/jugadores")
    public List<JugadorDTO> listarJugadores(@PathVariable("idCaravana") Long id) {
        logger.info("Lista de jugadores");
        return (List<JugadorDTO>) caravanaService.listarJugadores(id).orElseThrow();
    }


    @GetMapping("{idCaravana}")
    public CaravanaDTO buscarCaravana(@PathVariable("idCaravana") Long id) {
        logger.info("Vista de caravana");
        return caravanaService.buscarCaravana(id).orElseThrow();
    }

    @PostMapping
    public CaravanaDTO crearCaravana(@RequestBody CaravanaDTO caravanaDTO) {
        logger.info("Crear caravana");
        return caravanaService.crearCaravana(caravanaDTO);
    }

    @PutMapping
    public CaravanaDTO actualizarCaravana(@RequestBody CaravanaDTO caravanaDTO) {
        logger.info("Actualizar caravana");
        return caravanaService.actualizarCaravana(caravanaDTO);
    }

    @DeleteMapping("{idCaravana}")
    public void eliminarCaravana(@PathVariable("idCaravana") Long id) {
        logger.info("Eliminar caravana");
        caravanaService.eliminarCaravana(id);
    }
}
