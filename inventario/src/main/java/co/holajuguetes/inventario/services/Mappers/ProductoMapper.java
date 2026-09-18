package co.holajuguetes.inventario.services.Mappers;

import co.holajuguetes.inventario.models.Producto;
import co.holajuguetes.inventario.services.DTO.response.ProductoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductoMapper {

    private final GrupoVentaMapper grupoVentaMapper;
    private final ProveedorMapper proveedorMapper;

    public ProductoResponse toResponse(Producto producto) {
        if (producto == null) {
            return null;
        }
        return ProductoResponse.builder()
                .id(producto.getId())
                .sku(producto.getSku())
                .nombre(producto.getNombre())
                .cantidad(producto.getCantidad())
                .precioCosto(producto.getPrecioCosto())
                .activo(producto.getActivo())
                .grupoVenta(grupoVentaMapper.toResumenResponse(producto.getGrupoVenta()))
                .proveedor(producto.getProveedor() != null ? proveedorMapper.toResumenResponse(producto.getProveedor()) : null)
                .creadoEn(producto.getCreadoEn())
                .build();
    }
}
