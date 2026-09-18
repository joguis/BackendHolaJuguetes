package co.holajuguetes.inventario.services.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrupoVentaResumenResponse {
    private Integer id;
    private String nombre;
    private String codigoBarras;
    private BigDecimal precioVenta;
}
