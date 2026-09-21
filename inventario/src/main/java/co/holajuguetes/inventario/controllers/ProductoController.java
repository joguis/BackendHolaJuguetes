package co.holajuguetes.inventario.controllers;

import co.holajuguetes.inventario.services.DTO.*;
import co.holajuguetes.inventario.services.services.InventarioCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {
    private final InventarioCrudService service;
    @PostMapping public ResponseEntity<ProductoResponse> crear(@RequestBody OperacionRequest r) { return ResponseEntity.ok(service.crearProducto(r)); }
    @GetMapping public ResponseEntity<?> listar(@RequestBody(required = false) OperacionRequest r) { return ResponseEntity.ok(PaginaResponse.fromPage(service.listarProductos(r == null ? new OperacionRequest() : r))); }
    @PostMapping("/buscar") public ResponseEntity<?> buscar(@RequestBody OperacionRequest r) { return ResponseEntity.ok(PaginaResponse.fromPage(service.buscarProductos(r))); }
    @PatchMapping public ResponseEntity<ProductoResponse> editar(@RequestBody OperacionRequest r) { return ResponseEntity.ok(service.editarProducto(r)); }
    @DeleteMapping public ResponseEntity<Void> eliminar(@RequestBody OperacionRequest r) { service.eliminarProducto(r); return ResponseEntity.noContent().build(); }
}
