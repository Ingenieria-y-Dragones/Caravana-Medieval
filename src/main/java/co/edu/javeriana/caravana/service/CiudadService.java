package co.edu.javeriana.caravana.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.javeriana.caravana.dto.CiudadDTO;
import co.edu.javeriana.caravana.mapper.CiudadMapper;
import co.edu.javeriana.caravana.model.Ciudad;
import co.edu.javeriana.caravana.repository.CiudadRepository;

@Service
public class CiudadService {
    @Autowired
    private CiudadRepository ciudadRepository;

    public List<CiudadDTO> listarCiudades() {
        return ciudadRepository.findAll().stream()
                .map(CiudadMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CiudadDTO buscarCiudad(Long id) {
        return ciudadRepository.findById(id)
                .map(CiudadMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
    }

    public void guardarCiudad(CiudadDTO ciudadDTO) {
        Ciudad ciudad = CiudadMapper.toEntity(ciudadDTO);
        ciudadRepository.save(ciudad);
    }

    public void actualizarCiudad(Long id, CiudadDTO ciudadDTO) {
        Ciudad ciudadExistente = ciudadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

        ciudadExistente.setNombre(ciudadDTO.getNombre());
        ciudadExistente.setImpuesto(ciudadDTO.getImpuesto());

        ciudadRepository.save(ciudadExistente);
    }

    @Transactional
    public void eliminarCiudad(Long id) {
        ciudadRepository.deleteById(id);
    }
    public List<Ciudad> listarCiudadesEntidad() {
        return ciudadRepository.findAll();
    }

}

