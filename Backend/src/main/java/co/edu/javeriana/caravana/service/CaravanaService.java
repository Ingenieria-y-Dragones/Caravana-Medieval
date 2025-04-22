package co.edu.javeriana.caravana.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana.dto.CaravanaDTO;
import co.edu.javeriana.caravana.dto.InventarioCaravanaDTO;
import co.edu.javeriana.caravana.dto.JugadorDTO;
import co.edu.javeriana.caravana.dto.ProductoDTO;
import co.edu.javeriana.caravana.mapper.CaravanaMapper;
import co.edu.javeriana.caravana.mapper.JugadorMapper;
import co.edu.javeriana.caravana.model.Caravana;
import co.edu.javeriana.caravana.model.InventarioCaravana;
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

   public List<InventarioCaravanaDTO> obtenerInventarioCaravana(Long idCaravana) {
    return caravanaRepository.findById(idCaravana)
        .map(c -> c.getProductos().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList()))
        .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
    }

    private InventarioCaravanaDTO convertToDTO(InventarioCaravana inventario) {
        InventarioCaravanaDTO dto = new InventarioCaravanaDTO();
        dto.setId(inventario.getId());
        dto.setCantidad(inventario.getExistencias());
        
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(inventario.getProducto().getId());
        productoDTO.setNombre(inventario.getProducto().getNombre());
        productoDTO.setTipo(inventario.getProducto().getTipo());  // Usar el enum directamente
        
        dto.setProducto(productoDTO);
        return dto;
    }    
    
}