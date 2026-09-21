package co.holajuguetes.inventario.services.DTO;

import co.holajuguetes.inventario.enums.TipoMovimiento;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OperacionRequest {
    private Integer id;
    private String nombre;
    private String descripcion;
    private String telefono;
    private String email;
    private Integer categoriaId;
    private Integer subcategoriaId;
    private BigDecimal precioVenta;
    private String sku;
    private Integer grupoVentaId;
    private Integer proveedorId;
    private BigDecimal precioCosto;
    private Integer cantidad;
    private Boolean activo;
    private Integer productoId;
    private TipoMovimiento tipoMovimiento;
    private String motivo;
    private String referencia;
    private String termino;
    private Integer page;
    private Integer size;
}
