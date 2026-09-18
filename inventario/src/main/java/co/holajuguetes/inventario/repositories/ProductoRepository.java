package co.holajuguetes.inventario.repositories;

import co.holajuguetes.inventario.models.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Override
    @EntityGraph(attributePaths = {"grupoVenta", "proveedor"})
    Page<Producto> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"grupoVenta", "proveedor"})
    Page<Producto> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);

    @EntityGraph(attributePaths = {"grupoVenta", "proveedor"})
    Page<Producto> findBySkuContainingIgnoreCase(String sku, Pageable pageable);

    @EntityGraph(attributePaths = {"grupoVenta", "proveedor"})
    Page<Producto> findByActivoTrue(Pageable pageable);

    @EntityGraph(attributePaths = {"grupoVenta", "proveedor"})
    Optional<Producto> findBySku(String sku);
}
