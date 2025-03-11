package co.edu.javeriana.caravana.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana.model.Ciudad;
import co.edu.javeriana.caravana.repository.CiudadRepository;

@Service
public class CiudadService {
    @Autowired
    private CiudadRepository ciudadRepository;

    public List<Ciudad> listarCiudades(){
        return ciudadRepository.findAll();
    }

    public Ciudad buscarCiudad(Long id) {
        return ciudadRepository.findById(id).orElseThrow();
    }
}
