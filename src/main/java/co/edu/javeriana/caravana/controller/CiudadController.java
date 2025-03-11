package co.edu.javeriana.caravana.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.javeriana.caravana.model.Ciudad;
import co.edu.javeriana.caravana.service.CiudadService;

@Controller
@RequestMapping("/ciudad")
public class CiudadController {
    @Autowired
    private CiudadService ciudadService;

    @GetMapping("/list")
    void listarCiudades(){
        List<Ciudad> ciudades = ciudadService.listarCiudades();
        // ModelAndView modelAndView = new ModelAndView("ciudad-list");
    }
}
