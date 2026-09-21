package co.holajuguetes.inventario.services.Mappers;

import co.holajuguetes.inventario.models.GrupoVenta;
import co.holajuguetes.inventario.services.DTO.GrupoVentaResponse;
import co.holajuguetes.inventario.services.DTO.GrupoVentaResumenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GrupoVentaMapper {

    private final CategoriaMapper categoriaMapper;
    private final SubcategoriaMapper subcategoriaMapper;

    public GrupoVentaResponse toResponse(GrupoVenta grupoVenta) {
        if (grupoVenta == null) {
            return null;
        }
        return GrupoVentaResponse.builder()
                .id(grupoVenta.getId())
                .nombre(grupoVenta.getNombre())
                .categoria(categoriaMapper.toResponse(grupoVenta.getCategoria()))
                .subcategoria(subcategoriaMapper.toResponse(grupoVenta.getSubcategoria()))
                .codigoBarras(grupoVenta.getCodigoBarras())
                .precioVenta(grupoVenta.getPrecioVenta())
                .creadoEn(grupoVenta.getCreadoEn())
                .build();
    }

    public GrupoVentaResumenResponse toResumenResponse(GrupoVenta grupoVenta) {
        if (grupoVenta == null) {
            return null;
        }
        return GrupoVentaResumenResponse.builder()
                .id(grupoVenta.getId())
                .nombre(grupoVenta.getNombre())
                .codigoBarras(grupoVenta.getCodigoBarras())
                .precioVenta(grupoVenta.getPrecioVenta())
                .build();
    }
}
