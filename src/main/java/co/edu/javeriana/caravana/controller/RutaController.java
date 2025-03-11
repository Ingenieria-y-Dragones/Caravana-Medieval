package co.edu.javeriana.caravana.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.edu.javeriana.caravana.model.Ruta;
import co.edu.javeriana.caravana.service.RutaService;

@Controller
@RequestMapping("/ruta")
public class RutaController {
    @Autowired
    private RutaService rutaService;

    @GetMapping("/list")
    public ModelAndView listarRutas(){
        List<Ruta> rutas = rutaService.listarRutas();
        
        ModelAndView modelAndView = new ModelAndView("ruta-list");
        modelAndView.addObject("rutas", rutas);
        return modelAndView;
    }

    @GetMapping("/view/{idRuta}")
    public ModelAndView buscarRuta(@PathVariable("idRuta") Long id) {
        Ruta ruta = rutaService.buscarRuta(id);
        ModelAndView modelAndView = new ModelAndView("ruta-view");

        modelAndView.addObject("ruta", ruta);
        return modelAndView;
    }
}
