package co.holajuguetes.inventario.controllers;

import co.holajuguetes.inventario.services.DTO.*;
import co.holajuguetes.inventario.services.services.InventarioCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movimientos-inventario")
@RequiredArgsConstructor
public class MovimientoInventarioController {
    private final InventarioCrudService service;
    @PostMapping public ResponseEntity<MovimientoInventarioResponse> crear(@RequestBody OperacionRequest r){return ResponseEntity.ok(service.crearMovimiento(r));}
    @GetMapping public ResponseEntity<?> listar(@RequestBody(required=false) OperacionRequest r){return ResponseEntity.ok(PaginaResponse.fromPage(service.listarMovimientos(r==null?new OperacionRequest():r)));}
    @PostMapping("/buscar") public ResponseEntity<?> buscar(@RequestBody OperacionRequest r){return ResponseEntity.ok(PaginaResponse.fromPage(service.buscarMovimientos(r)));}
    @PostMapping("/siguiente-referencia") public ResponseEntity<String> siguiente(@RequestBody OperacionRequest r){return ResponseEntity.ok(service.avanzarReferencia(r.getTipoMovimiento()));}
    @PatchMapping public ResponseEntity<MovimientoInventarioResponse> editar(@RequestBody OperacionRequest r){return ResponseEntity.ok(service.editarMovimiento(r));}
    @DeleteMapping public ResponseEntity<Void> eliminar(@RequestBody OperacionRequest r){service.eliminarMovimiento(r);return ResponseEntity.noContent().build();}
}
