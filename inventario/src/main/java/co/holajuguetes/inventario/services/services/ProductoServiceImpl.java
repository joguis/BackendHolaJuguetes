package co.holajuguetes.inventario.services.services;

import co.holajuguetes.inventario.models.Producto;
import co.holajuguetes.inventario.repositories.ProductoRepository;
import co.holajuguetes.inventario.services.DTO.response.ProductoResponse;
import co.holajuguetes.inventario.services.Mappers.ProductoMapper;
import co.holajuguetes.inventario.services.interfaces.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Override
    public Page<ProductoResponse> listarProductos(Pageable pageable) {
        Page<Producto> productos = productoRepository.findAll(pageable);
        return productos.map(productoMapper::toResponse);
    }

    @Override
    public Page<ProductoResponse> buscarPorNombre(String nombre, Pageable pageable) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El término de búsqueda por nombre no puede estar vacío");
        }
        Page<Producto> productos = productoRepository.findByNombreContainingIgnoreCase(nombre.trim(), pageable);
        return productos.map(productoMapper::toResponse);
    }

    @Override
    public Page<ProductoResponse> buscarPorSku(String sku, Pageable pageable) {
        if (sku == null || sku.trim().isEmpty()) {
            throw new IllegalArgumentException("El término de búsqueda por SKU no puede estar vacío");
        }
        Page<Producto> productos = productoRepository.findBySkuContainingIgnoreCase(sku.trim(), pageable);
        return productos.map(productoMapper::toResponse);
    }

    @Override
    public Page<ProductoResponse> listarActivos(Pageable pageable) {
        Page<Producto> productos = productoRepository.findByActivoTrue(pageable);
        return productos.map(productoMapper::toResponse);
    }
}
