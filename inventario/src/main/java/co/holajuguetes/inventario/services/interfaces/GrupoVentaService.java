package co.holajuguetes.inventario.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.holajuguetes.inventario.services.DTO.GrupoVentaResponse;

public interface GrupoVentaService {

    Page<GrupoVentaResponse> listarGruposVenta(Pageable pageable);

    Page<GrupoVentaResponse> buscarPorNombre(String nombre, Pageable pageable);

    GrupoVentaResponse buscarPorCodigoBarras(String codigoBarras);
}
