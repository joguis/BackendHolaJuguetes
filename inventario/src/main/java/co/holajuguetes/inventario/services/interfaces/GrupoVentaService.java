package co.holajuguetes.inventario.services.interfaces;

import co.holajuguetes.inventario.services.DTO.response.GrupoVentaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GrupoVentaService {

    Page<GrupoVentaResponse> listarGruposVenta(Pageable pageable);

    Page<GrupoVentaResponse> buscarPorNombre(String nombre, Pageable pageable);

    GrupoVentaResponse buscarPorCodigoBarras(String codigoBarras);
}
