package co.holajuguetes.inventario.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponse {
    private Integer id;
    private String sku;
    private String nombre;
    private Integer cantidad;
    private BigDecimal precioCosto;
    private Boolean activo;
    private GrupoVentaResumenResponse grupoVenta;
    private ProveedorResumenResponse proveedor;
    private OffsetDateTime creadoEn;
}
