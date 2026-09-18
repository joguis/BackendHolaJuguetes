package co.holajuguetes.inventario.services.Mappers;

import co.holajuguetes.inventario.models.Proveedor;
import co.holajuguetes.inventario.services.DTO.response.ProveedorResumenResponse;
import org.springframework.stereotype.Component;

@Component
public class ProveedorMapper {

    public ProveedorResumenResponse toResumenResponse(Proveedor proveedor) {
        if (proveedor == null) {
            return null;
        }
        return ProveedorResumenResponse.builder()
                .id(proveedor.getId())
                .nombre(proveedor.getNombre())
                .build();
    }
}
