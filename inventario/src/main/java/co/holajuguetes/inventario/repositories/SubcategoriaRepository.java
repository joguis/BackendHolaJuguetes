package co.holajuguetes.inventario.repositories;

import co.holajuguetes.inventario.models.Subcategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Integer> {
    Optional<Subcategoria> findByNombreIgnoreCase(String nombre);
    boolean existsByNombreIgnoreCase(String nombre);
    Page<Subcategoria> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
