package co.holajuguetes.inventario.repositories;

import co.holajuguetes.inventario.models.GrupoVenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GrupoVentaRepository extends JpaRepository<GrupoVenta, Integer> {

    @Override
    @EntityGraph(attributePaths = {"categoria", "subcategoria"})
    Page<GrupoVenta> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"categoria", "subcategoria"})
    Page<GrupoVenta> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);

    @EntityGraph(attributePaths = {"categoria", "subcategoria"})
    Optional<GrupoVenta> findByCodigoBarras(String codigoBarras);
}
