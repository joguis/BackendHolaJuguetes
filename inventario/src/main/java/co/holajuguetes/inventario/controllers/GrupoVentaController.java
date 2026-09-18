package co.holajuguetes.inventario.controllers;

import co.holajuguetes.inventario.services.DTO.response.GrupoVentaResponse;
import co.holajuguetes.inventario.services.DTO.response.PaginaResponse;
import co.holajuguetes.inventario.services.interfaces.GrupoVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/grupos-venta")
@RequiredArgsConstructor
public class GrupoVentaController {

    private final GrupoVentaService grupoVentaService;

    @GetMapping
    public ResponseEntity<PaginaResponse<GrupoVentaResponse>> listarGruposVenta(
            @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(PaginaResponse.fromPage(grupoVentaService.listarGruposVenta(pageable)));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<PaginaResponse<GrupoVentaResponse>> buscarPorNombre(
            @PathVariable String nombre,
            @PageableDefault(size = 20, sort = "nombre") Pageable pageable) {
        return ResponseEntity.ok(PaginaResponse.fromPage(grupoVentaService.buscarPorNombre(nombre, pageable)));
    }

    @GetMapping("/codigo-barras/{codigoBarras}")
    public ResponseEntity<GrupoVentaResponse> buscarPorCodigoBarras(
            @PathVariable String codigoBarras) {
        return ResponseEntity.ok(grupoVentaService.buscarPorCodigoBarras(codigoBarras));
    }
}
