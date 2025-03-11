package co.edu.javeriana.caravana.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.edu.javeriana.caravana.model.Ciudad;
import co.edu.javeriana.caravana.model.Ruta;
import co.edu.javeriana.caravana.service.CiudadService;
import co.edu.javeriana.caravana.service.RutaService;

@Controller
@RequestMapping("/ciudad")
public class CiudadController {
    @Autowired
    private CiudadService ciudadService;

    @Autowired
    private RutaService rutaService; 

    @GetMapping("/list")
    public ModelAndView listarCiudades(){
        List<Ciudad> ciudades = ciudadService.listarCiudades();
        
        ModelAndView modelAndView = new ModelAndView("ciudad-list");
        modelAndView.addObject("ciudades", ciudades);
        return modelAndView;
    }

    @GetMapping("/view/{idCiudad}")
    public ModelAndView buscarCiudad(@PathVariable("idCiudad") Long id) {
        Ciudad ciudad = ciudadService.buscarCiudad(id);
        ModelAndView modelAndView = new ModelAndView("ciudad-view");

        modelAndView.addObject("ciudad", ciudad);
        return modelAndView;
    }

    @GetMapping("/view/ruta/{idCiudad}")
    public ModelAndView listarRutasSalientes(@PathVariable("idCiudad") Long idCiudad) {
        Ciudad ciudad = ciudadService.buscarCiudad(idCiudad);
        List<Ruta> rutas = rutaService.listarRutasPorCiudad(idCiudad); // Nuevo método

        ModelAndView modelAndView = new ModelAndView("rutas-ciudad-view");
        modelAndView.addObject("ciudad", ciudad);
        modelAndView.addObject("rutas", rutas);
        return modelAndView;
    }

}
