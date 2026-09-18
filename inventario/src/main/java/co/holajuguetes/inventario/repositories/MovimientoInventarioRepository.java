package co.holajuguetes.inventario.repositories;

import co.holajuguetes.inventario.models.MovimientoInventario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Integer> {

    @EntityGraph(attributePaths = {"producto"})
    List<MovimientoInventario> findByProductoId(Integer productoId);

    @EntityGraph(attributePaths = {"producto"})
    Page<MovimientoInventario> findByProductoId(Integer productoId, Pageable pageable);
}
