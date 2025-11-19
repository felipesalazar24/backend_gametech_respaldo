package Print3D.Inventario.DTOs;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private UUID id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String categoria;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    // Ejemplo de campo calculado
    public String getPrecioFormateado() {
        return String.format("$%.2f", precio);
    }
}