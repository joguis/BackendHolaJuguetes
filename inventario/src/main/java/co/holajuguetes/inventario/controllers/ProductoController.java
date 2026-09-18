package co.holajuguetes.inventario.controllers;

import co.holajuguetes.inventario.services.DTO.response.PaginaResponse;
import co.holajuguetes.inventario.services.DTO.response.ProductoResponse;
import co.holajuguetes.inventario.services.interfaces.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<PaginaResponse<ProductoResponse>> listarProductos(
            @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(PaginaResponse.fromPage(productoService.listarProductos(pageable)));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<PaginaResponse<ProductoResponse>> buscarPorNombre(
            @PathVariable String nombre,
            @PageableDefault(size = 20, sort = "nombre") Pageable pageable) {
        return ResponseEntity.ok(PaginaResponse.fromPage(productoService.buscarPorNombre(nombre, pageable)));
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<PaginaResponse<ProductoResponse>> buscarPorSku(
            @PathVariable String sku,
            @PageableDefault(size = 20, sort = "sku") Pageable pageable) {
        return ResponseEntity.ok(PaginaResponse.fromPage(productoService.buscarPorSku(sku, pageable)));
    }

    @GetMapping("/activos")
    public ResponseEntity<PaginaResponse<ProductoResponse>> listarActivos(
            @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(PaginaResponse.fromPage(productoService.listarActivos(pageable)));
    }
}
