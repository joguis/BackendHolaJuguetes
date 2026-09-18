package co.holajuguetes.inventario.services.interfaces;

import co.holajuguetes.inventario.services.DTO.response.ProductoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductoService {

    Page<ProductoResponse> listarProductos(Pageable pageable);

    Page<ProductoResponse> buscarPorNombre(String nombre, Pageable pageable);

    Page<ProductoResponse> buscarPorSku(String sku, Pageable pageable);

    Page<ProductoResponse> listarActivos(Pageable pageable);
}
