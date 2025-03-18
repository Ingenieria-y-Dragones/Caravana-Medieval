package co.edu.javeriana.caravana.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import co.edu.javeriana.caravana.dto.RutaDTO;
import co.edu.javeriana.caravana.model.Ciudad;
import co.edu.javeriana.caravana.service.CiudadService;
import co.edu.javeriana.caravana.service.RutaService;

@Controller
@RequestMapping("/ruta")
public class RutaController {
    @Autowired
    private RutaService rutaService;

    @Autowired
    private CiudadService ciudadService; // Agregamos el servicio para obtener las ciudades

    @GetMapping("/list")
    public ModelAndView listarRutas(){
        List<RutaDTO> rutas = rutaService.listarRutas();
        ModelAndView modelAndView = new ModelAndView("ruta-list");
        modelAndView.addObject("rutas", rutas);
        return modelAndView;
    }

    @GetMapping("/view/{idRuta}")
    public ModelAndView buscarRuta(@PathVariable("idRuta") Long id) {
        RutaDTO ruta = rutaService.buscarRuta(id).orElseThrow();
        ModelAndView modelAndView = new ModelAndView("ruta-view");
        modelAndView.addObject("ruta", ruta);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView formularioCrearRuta() {
        List<Ciudad> ciudades = ciudadService.listarCiudadesEntidad();

        ModelAndView modelAndView = new ModelAndView("ruta-edit");
        modelAndView.addObject("ruta", new RutaDTO());
        modelAndView.addObject("ciudades", ciudades);
        return modelAndView;
    }

    @GetMapping("/edit/{idRuta}")
    public ModelAndView formularioEditarRuta(@PathVariable("idRuta") Long id) {
        RutaDTO ruta = rutaService.buscarRuta(id).orElseThrow();
        List<Ciudad> ciudades = ciudadService.listarCiudadesEntidad();

        ModelAndView modelAndView = new ModelAndView("ruta-edit");
        modelAndView.addObject("ruta", ruta);
        modelAndView.addObject("ciudades", ciudades);
        return modelAndView;
    }


    @PostMapping("/save")
    public RedirectView guardarRuta(RutaDTO rutaDTO) {
        rutaService.guardarRuta(rutaDTO);
        return new RedirectView("/ruta/list");  
    }

    @GetMapping("/delete/{id}")
    public RedirectView borrarRuta(@PathVariable Long id) {
        rutaService.borrarRuta(id);
        return new RedirectView("/ruta/list");

    }
}

