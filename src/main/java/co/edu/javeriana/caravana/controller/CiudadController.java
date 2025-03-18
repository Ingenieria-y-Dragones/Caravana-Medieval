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

import co.edu.javeriana.caravana.dto.CiudadDTO;
import co.edu.javeriana.caravana.service.CiudadService;

@Controller
@RequestMapping("/ciudad")
public class CiudadController {
    @Autowired
    private CiudadService ciudadService;

    @GetMapping("/list")
    public ModelAndView listarCiudades() {
        List<CiudadDTO> ciudades = ciudadService.listarCiudades();
        ModelAndView modelAndView = new ModelAndView("ciudad-list");
        modelAndView.addObject("ciudades", ciudades);
        return modelAndView;
    }
    @GetMapping("/view/{idCiudad}")
    public ModelAndView buscarCiudad(@PathVariable("idCiudad") Long id) {
        CiudadDTO ciudad = ciudadService.buscarCiudad(id);
        ModelAndView modelAndView = new ModelAndView("ciudad-view");
        modelAndView.addObject("ciudad", ciudad);
        return modelAndView;
    }


    @GetMapping("/create")
    public ModelAndView formularioCrearCiudad() {
        ModelAndView modelAndView = new ModelAndView("ciudad-edit");
        modelAndView.addObject("ciudad", new CiudadDTO());
        return modelAndView;
    }

    @GetMapping("/edit/{idCiudad}")
    public ModelAndView formularioEditarCiudad(@PathVariable("idCiudad") Long id) {
        CiudadDTO ciudad = ciudadService.buscarCiudad(id);
        ModelAndView modelAndView = new ModelAndView("ciudad-edit");
        modelAndView.addObject("ciudad", ciudad);
        return modelAndView;
    }

    @PostMapping("/update/{idCiudad}")
    public RedirectView actualizarCiudad(@PathVariable("idCiudad") Long id, CiudadDTO ciudadDTO) {
        ciudadService.actualizarCiudad(id, ciudadDTO);
        return new RedirectView("/ciudad/list");
    }

    @PostMapping("/save")
    public RedirectView guardarCiudad(CiudadDTO ciudadDTO) {
        ciudadService.guardarCiudad(ciudadDTO);
        return new RedirectView("/ciudad/list");
    }

    @GetMapping("/delete/{idCiudad}")
    public RedirectView eliminarCiudad(@PathVariable("idCiudad") Long id) {
        ciudadService.eliminarCiudad(id);
        return new RedirectView("/ciudad/list");
    }
}

