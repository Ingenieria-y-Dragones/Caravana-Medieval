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

import co.edu.javeriana.caravana.dto.RutaDTO;
import co.edu.javeriana.caravana.service.RutaService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ruta")
public class RutaController {
    @Autowired
    private RutaService rutaService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/lista")
    public List<RutaDTO> listarRutas() {
        logger.info("Lista de rutas");
        return rutaService.listarRutas();
    }

    @GetMapping("{idRuta}")
    public RutaDTO buscarRuta(@PathVariable("idRuta") Long id) {
        logger.info("Vista de ruta");
        return rutaService.buscarRuta(id).orElseThrow();
    }

    @PostMapping
    public RutaDTO crearRuta(@RequestBody RutaDTO rutaDTO) {
        logger.info("Crear ruta");
        return rutaService.crearRuta(rutaDTO);
    }

    @PutMapping
    public RutaDTO actualizarRuta(@RequestBody RutaDTO rutaDTO) {
        logger.info("Actualizar ruta");
        return rutaService.actualizarRuta(rutaDTO);
    }

    @DeleteMapping("{idRuta}")
    public void eliminarRuta(@PathVariable("idRuta") Long id) {
        logger.info("Eliminar ruta");
        rutaService.eliminarRuta(id);
    }
}
