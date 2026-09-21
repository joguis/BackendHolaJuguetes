package co.holajuguetes.inventario.services.Mappers;

import co.holajuguetes.inventario.models.Categoria;
import co.holajuguetes.inventario.services.DTO.CategoriaResponse;

import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaResponse toResponse(Categoria categoria) {
        if (categoria == null) {
            return null;
        }
        return CategoriaResponse.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .build();
    }
}
