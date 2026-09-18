package co.holajuguetes.inventario.services.DTO.response;

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
public class GrupoVentaResponse {
    private Integer id;
    private String nombre;
    private CategoriaResponse categoria;
    private SubcategoriaResponse subcategoria;
    private String codigoBarras;
    private BigDecimal precioVenta;
    private OffsetDateTime creadoEn;
}
