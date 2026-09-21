package co.holajuguetes.inventario.controllers;

import co.holajuguetes.inventario.services.DTO.*;
import co.holajuguetes.inventario.services.services.InventarioCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proveedores")
@RequiredArgsConstructor
public class ProveedorController {
    private final InventarioCrudService service;
    @PostMapping public ResponseEntity<ProveedorResumenResponse> crear(@RequestBody OperacionRequest r){return ResponseEntity.ok(service.crearProveedor(r));}
    @GetMapping public ResponseEntity<?> listar(@RequestBody(required=false) OperacionRequest r){return ResponseEntity.ok(service.listarProveedores(r==null?new OperacionRequest():r));}
    @PostMapping("/buscar") public ResponseEntity<?> buscar(@RequestBody OperacionRequest r){return ResponseEntity.ok(service.buscarProveedores(r));}
    @PatchMapping public ResponseEntity<ProveedorResumenResponse> editar(@RequestBody OperacionRequest r){return ResponseEntity.ok(service.editarProveedor(r));}
    @DeleteMapping public ResponseEntity<Void> eliminar(@RequestBody OperacionRequest r){service.eliminarProveedor(r);return ResponseEntity.noContent().build();}
}
