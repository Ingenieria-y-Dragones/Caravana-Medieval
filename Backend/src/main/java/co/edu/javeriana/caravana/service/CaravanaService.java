package co.edu.javeriana.caravana.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana.dto.CaravanaDTO;
import co.edu.javeriana.caravana.dto.JugadorDTO;
import co.edu.javeriana.caravana.mapper.CaravanaMapper;
import co.edu.javeriana.caravana.mapper.JugadorMapper;
import co.edu.javeriana.caravana.model.Caravana;
import co.edu.javeriana.caravana.repository.CaravanaRepository;

@Service
public class CaravanaService {
    @Autowired
    private CaravanaRepository caravanaRepository;

    public Optional<List<JugadorDTO>> listarJugadores(Long caravanaId) {
        Optional<Caravana> caravanaOptional = caravanaRepository.findById(caravanaId);

        if (caravanaOptional.isEmpty()) {
            return Optional.empty();
        }

        Caravana caravana = caravanaOptional.get();

        List<JugadorDTO> jugadoresDTOs = caravana.getJugadores().stream()
                .map(JugadorMapper::toDTO)
                .toList();

        return Optional.of(jugadoresDTOs);
    }


    public Optional<CaravanaDTO> buscarCaravana(Long id) {
        return caravanaRepository.findById(id).map(CaravanaMapper::toDTO);
    }

    public CaravanaDTO crearCaravana(CaravanaDTO caravanaDTO) {
        caravanaDTO.setId(null);
        Caravana caravana = CaravanaMapper.toEntity(caravanaDTO);
        return CaravanaMapper.toDTO(caravanaRepository.save(caravana));
    }

    public CaravanaDTO actualizarCaravana(CaravanaDTO caravanaDTO) {
        //TODO checkear que el id sea null
        Caravana caravana = CaravanaMapper.toEntity(caravanaDTO);
        return CaravanaMapper.toDTO(caravanaRepository.save(caravana));
    }

    public void eliminarCaravana(Long id) {
        caravanaRepository.deleteById(id);
    }
}