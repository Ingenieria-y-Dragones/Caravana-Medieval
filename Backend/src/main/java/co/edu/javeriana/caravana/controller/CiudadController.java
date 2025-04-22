package co.edu.javeriana.caravana.controller;

import co.edu.javeriana.caravana.dto.InventarioCiudadDTO;
import co.edu.javeriana.caravana.dto.ServicioOfrecidoDTO;
import co.edu.javeriana.caravana.service.CiudadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.ResponseEntity;

import co.edu.javeriana.caravana.dto.CiudadDTO;

@RestController
@RequestMapping("/ciudad")
@CrossOrigin(origins = "http://localhost:4200")
public class CiudadController {

    private final CiudadService ciudadService;

    @Autowired
    public CiudadController(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CiudadDTO> obtenerCiudad(@PathVariable Long id) {
        return ciudadService.obtenerCiudad(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/productos")
    public List<InventarioCiudadDTO> obtenerProductosCiudad(@PathVariable Long id) {
        return ciudadService.obtenerProductosCiudad(id);
    }

    @GetMapping("/{id}/servicios")
    public List<ServicioOfrecidoDTO> obtenerServiciosCiudad(@PathVariable Long id) {
        return ciudadService.obtenerServiciosCiudad(id);
    }
}
