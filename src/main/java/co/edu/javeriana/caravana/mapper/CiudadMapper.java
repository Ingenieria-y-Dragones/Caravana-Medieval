package co.edu.javeriana.caravana.mapper;

import co.edu.javeriana.caravana.dto.CiudadDTO;
import co.edu.javeriana.caravana.model.Ciudad;

public class CiudadMapper {
    public static CiudadDTO toDTO(Ciudad ciudad) {
        return new CiudadDTO(
                ciudad.getId(),
                ciudad.getNombre(),
                ciudad.getImpuesto(),
                ciudad.getRutasEntrantes(),
                ciudad.getRutasSalientes()
        );
    }

    public static Ciudad toEntity(CiudadDTO ciudadDTO) {
        Ciudad ciudad = new Ciudad();
        ciudad.setId(ciudadDTO.getId());
        ciudad.setNombre(ciudadDTO.getNombre());
        ciudad.setImpuesto(ciudadDTO.getImpuesto());

        return ciudad;
    }
}

