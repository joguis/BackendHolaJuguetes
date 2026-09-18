package co.holajuguetes.inventario.services.DTO.response;

import co.holajuguetes.inventario.enums.TipoMovimiento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoInventarioResponse {
    private Integer id;
    private Integer productoId;
    private String productoNombre;
    private TipoMovimiento tipoMovimiento;
    private Integer cantidad;
    private String motivo;
    private String referencia;
    private OffsetDateTime creadoEn;
}
