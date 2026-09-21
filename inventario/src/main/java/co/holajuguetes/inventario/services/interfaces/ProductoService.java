package co.holajuguetes.inventario.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.holajuguetes.inventario.services.DTO.ProductoResponse;

public interface ProductoService {

    Page<ProductoResponse> listarProductos(Pageable pageable);

    Page<ProductoResponse> buscarPorNombre(String nombre, Pageable pageable);

    Page<ProductoResponse> buscarPorSku(String sku, Pageable pageable);

    Page<ProductoResponse> listarActivos(Pageable pageable);
}
