package co.holajuguetes.inventario.controllers;

import co.holajuguetes.inventario.services.DTO.*;
import co.holajuguetes.inventario.services.services.InventarioCrudService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subcategorias")
@RequiredArgsConstructor
public class SubcategoriaController {

    private final InventarioCrudService service;

    @PostMapping
    public ResponseEntity<SubcategoriaResponse> crear(
            @RequestBody OperacionRequest r
    ) {
        return ResponseEntity.ok(service.crearSubcategoria(r));
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
                        service.listarSubcategorias(r)
                )
        );
    }

    @PostMapping("/buscar")
    public ResponseEntity<?> buscar(
            @RequestBody OperacionRequest r
    ) {
        return ResponseEntity.ok(
                PaginaResponse.fromPage(
                        service.buscarSubcategorias(r)
                )
        );
    }

    @PatchMapping
    public ResponseEntity<SubcategoriaResponse> editar(
            @RequestBody OperacionRequest r
    ) {
        return ResponseEntity.ok(service.editarSubcategoria(r));
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(
            @RequestBody OperacionRequest r
    ) {
        service.eliminarSubcategoria(r);
        return ResponseEntity.noContent().build();
    }
}