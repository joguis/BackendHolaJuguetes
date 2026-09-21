package co.holajuguetes.inventario.services.Mappers;

import co.holajuguetes.inventario.models.Subcategoria;
import co.holajuguetes.inventario.services.DTO.SubcategoriaResponse;

import org.springframework.stereotype.Component;

@Component
public class SubcategoriaMapper {

    public SubcategoriaResponse toResponse(Subcategoria subcategoria) {
        if (subcategoria == null) {
            return null;
        }
        return SubcategoriaResponse.builder()
                .id(subcategoria.getId())
                .nombre(subcategoria.getNombre())
                .build();
    }
}
