package co.edu.javeriana.caravana.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana.model.Ruta;
import co.edu.javeriana.caravana.repository.RutaRepository;

@Service
public class RutaService {
    @Autowired
    private RutaRepository rutaRepository;

    public List<Ruta> listarRutas(){
        return rutaRepository.findAll();
    }

    public Ruta buscarRuta(Long id) {
        return rutaRepository.findById(id).orElseThrow();
    }



}
