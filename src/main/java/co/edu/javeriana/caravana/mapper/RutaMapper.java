package co.edu.javeriana.caravana.mapper;

import co.edu.javeriana.caravana.dto.RutaDTO;
import co.edu.javeriana.caravana.model.Ciudad;
import co.edu.javeriana.caravana.model.Ruta;
import co.edu.javeriana.caravana.repository.CiudadRepository;

public class RutaMapper {
    public static RutaDTO toDTO(Ruta ruta) {
        RutaDTO rutaDTO = new RutaDTO();
        rutaDTO.setId(ruta.getId());
        rutaDTO.setNombre(ruta.getNombre());
        rutaDTO.setCiudadOrigen(ruta.getCiudadOrigen().getNombre());  // Convertimos Ciudad a String
        rutaDTO.setCiudadDestino(ruta.getCiudadDestino().getNombre());
        rutaDTO.setDistancia(ruta.getDistancia());
        rutaDTO.setSegura(ruta.getSegura());
        rutaDTO.setDaño(ruta.getDaño());
        return rutaDTO;
    }

    public static Ruta toEntity(RutaDTO rutaDTO, CiudadRepository ciudadRepository) {
    Ruta ruta = new Ruta();
    ruta.setId(rutaDTO.getId());
    ruta.setNombre(rutaDTO.getNombre());
    ruta.setDistancia(rutaDTO.getDistancia());
    ruta.setSegura(rutaDTO.getSegura());
    ruta.setDaño(rutaDTO.getDaño());

    // Buscar las ciudades en la base de datos usando los nombres
    Ciudad ciudadOrigen = ciudadRepository.findByNombre(rutaDTO.getCiudadOrigen())
            .orElseThrow(() -> new RuntimeException("Ciudad de origen no encontrada: " + rutaDTO.getCiudadOrigen()));

    Ciudad ciudadDestino = ciudadRepository.findByNombre(rutaDTO.getCiudadDestino())
            .orElseThrow(() -> new RuntimeException("Ciudad de destino no encontrada: " + rutaDTO.getCiudadDestino()));

    // Asignar los objetos Ciudad a la ruta
    ruta.setCiudadOrigen(ciudadOrigen);
    ruta.setCiudadDestino(ciudadDestino);

    return ruta;
}

}
