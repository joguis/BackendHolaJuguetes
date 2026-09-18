package co.holajuguetes.inventario.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TipoMovimiento {
    ENTRADA("entrada"),
    SALIDA("salida"),
    AJUSTE("ajuste"),
    DEVOLUCION("devolucion");

    private final String valor;

    TipoMovimiento(String valor) {
        this.valor = valor;
    }

    @JsonValue
    public String getValor() {
        return valor;
    }

    @JsonCreator
    public static TipoMovimiento fromValor(String valor) {
        if (valor == null) {
            return null;
        }
        for (TipoMovimiento tipo : TipoMovimiento.values()) {
            if (tipo.valor.equalsIgnoreCase(valor) || tipo.name().equalsIgnoreCase(valor)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de movimiento no válido: " + valor);
    }
}
