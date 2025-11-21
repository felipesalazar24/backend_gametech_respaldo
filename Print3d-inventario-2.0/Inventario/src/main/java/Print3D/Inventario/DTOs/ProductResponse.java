package Print3D.Inventario.DTOs;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private int id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String categoria;

    // Ejemplo de campo calculado
    public String getPrecioFormateado() {
        return String.format("$%.2f", precio);
    }
}