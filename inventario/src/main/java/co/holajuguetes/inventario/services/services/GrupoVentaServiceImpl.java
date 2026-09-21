package co.holajuguetes.inventario.services.services;

import co.holajuguetes.inventario.exceptions.ResourceNotFoundException;
import co.holajuguetes.inventario.models.GrupoVenta;
import co.holajuguetes.inventario.repositories.GrupoVentaRepository;
import co.holajuguetes.inventario.services.DTO.GrupoVentaResponse;
import co.holajuguetes.inventario.services.Mappers.GrupoVentaMapper;
import co.holajuguetes.inventario.services.interfaces.GrupoVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GrupoVentaServiceImpl implements GrupoVentaService {

    private final GrupoVentaRepository grupoVentaRepository;
    private final GrupoVentaMapper grupoVentaMapper;

    @Override
    public Page<GrupoVentaResponse> listarGruposVenta(Pageable pageable) {
        Page<GrupoVenta> grupos = grupoVentaRepository.findAll(pageable);
        return grupos.map(grupoVentaMapper::toResponse);
    }

    @Override
    public Page<GrupoVentaResponse> buscarPorNombre(String nombre, Pageable pageable) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El término de búsqueda por nombre no puede estar vacío");
        }
        Page<GrupoVenta> grupos = grupoVentaRepository.findByNombreContainingIgnoreCase(nombre.trim(), pageable);
        return grupos.map(grupoVentaMapper::toResponse);
    }

    @Override
    public GrupoVentaResponse buscarPorCodigoBarras(String codigoBarras) {
        if (codigoBarras == null || codigoBarras.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de barras no puede estar vacío");
        }
        return grupoVentaRepository.findByCodigoBarras(codigoBarras.trim())
                .map(grupoVentaMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró ningún grupo de venta con el código de barras: " + codigoBarras));
    }
}
