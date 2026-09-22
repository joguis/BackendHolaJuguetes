package co.holajuguetes.inventario.controllers;

import co.holajuguetes.inventario.services.DTO.*;
import co.holajuguetes.inventario.services.services.InventarioCrudService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grupos-venta")
@RequiredArgsConstructor
public class GrupoVentaController {

    private final InventarioCrudService service;

    @PostMapping
    public ResponseEntity<GrupoVentaResponse> crear(
            @RequestBody OperacionRequest r
    ) {
        return ResponseEntity.ok(service.crearGrupo(r));
    }

    @GetMapping
    public ResponseEntity<?> listar(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size
    ) {
        OperacionRequest r = new OperacionRequest();
        r.setPage(page);
        r.setSize(size);

        return ResponseEntity.ok(
                PaginaResponse.fromPage(
                        service.listarGrupos(r)
                )
        );
    }

    @PostMapping("/buscar")
    public ResponseEntity<?> buscar(
            @RequestBody OperacionRequest r
    ) {
        return ResponseEntity.ok(
                PaginaResponse.fromPage(
                        service.buscarGrupos(r)
                )
        );
    }

    @PatchMapping
    public ResponseEntity<GrupoVentaResponse> editar(
            @RequestBody OperacionRequest r
    ) {
        return ResponseEntity.ok(service.editarGrupo(r));
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(
            @RequestBody OperacionRequest r
    ) {
        service.eliminarGrupo(r);
        return ResponseEntity.noContent().build();
    }
}