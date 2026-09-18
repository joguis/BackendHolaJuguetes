package co.holajuguetes.inventario.services.Mappers;

import co.holajuguetes.inventario.models.MovimientoInventario;
import co.holajuguetes.inventario.services.DTO.response.MovimientoInventarioResponse;
import org.springframework.stereotype.Component;

@Component
public class MovimientoInventarioMapper {

    public MovimientoInventarioResponse toResponse(MovimientoInventario movimiento) {
        if (movimiento == null) {
            return null;
        }
        return MovimientoInventarioResponse.builder()
                .id(movimiento.getId())
                .productoId(movimiento.getProducto() != null ? movimiento.getProducto().getId() : null)
                .productoNombre(movimiento.getProducto() != null ? movimiento.getProducto().getNombre() : null)
                .tipoMovimiento(movimiento.getTipoMovimiento())
                .cantidad(movimiento.getCantidad())
                .motivo(movimiento.getMotivo())
                .referencia(movimiento.getReferencia())
                .creadoEn(movimiento.getCreadoEn())
                .build();
    }
}
