package co.edu.javeriana.caravana.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana.dto.RutaDTO;
import co.edu.javeriana.caravana.mapper.RutaMapper;
import co.edu.javeriana.caravana.model.Ruta;
import co.edu.javeriana.caravana.repository.CiudadRepository;
import co.edu.javeriana.caravana.repository.RutaRepository;

@Service
public class RutaService {
    @Autowired
    private RutaRepository rutaRepository;
    @Autowired
    private CiudadRepository ciudadRepository;

    public List<RutaDTO> listarRutas(){
        return rutaRepository.findAll().stream()
        .map(RutaMapper::toDTO).toList();
    }

    public Optional <RutaDTO> buscarRuta(Long id) {
        return rutaRepository.findById(id).map(RutaMapper::toDTO);
    }

    public void guardarRuta(RutaDTO rutaDTO) {
        Ruta ruta = RutaMapper.toEntity(rutaDTO, ciudadRepository);
        rutaRepository.save(ruta);
    }

    public void borrarPersona(Long id) {
        rutaRepository.deleteById(id);
    }
    



}
