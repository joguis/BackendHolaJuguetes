package co.holajuguetes.inventario.controllers;

import co.holajuguetes.inventario.services.DTO.*;
import co.holajuguetes.inventario.services.services.InventarioCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final InventarioCrudService service;
    @PostMapping public ResponseEntity<CategoriaResponse> crear(@RequestBody OperacionRequest r){return ResponseEntity.ok(service.crearCategoria(r));}
    @GetMapping public ResponseEntity<?> listar(@RequestBody(required=false) OperacionRequest r){return ResponseEntity.ok(PaginaResponse.fromPage(service.listarCategorias(r==null?new OperacionRequest():r)));}
    @PostMapping("/buscar") public ResponseEntity<?> buscar(@RequestBody OperacionRequest r){return ResponseEntity.ok(PaginaResponse.fromPage(service.buscarCategorias(r)));}
    @PatchMapping public ResponseEntity<CategoriaResponse> editar(@RequestBody OperacionRequest r){return ResponseEntity.ok(service.editarCategoria(r));}
    @DeleteMapping public ResponseEntity<Void> eliminar(@RequestBody OperacionRequest r){service.eliminarCategoria(r);return ResponseEntity.noContent().build();}
}
