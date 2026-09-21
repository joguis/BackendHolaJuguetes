package co.holajuguetes.inventario.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubcategoriaResponse {
    private Integer id;
    private String nombre;
}
