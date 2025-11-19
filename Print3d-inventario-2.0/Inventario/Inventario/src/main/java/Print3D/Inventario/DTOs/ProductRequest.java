package Print3D.Inventario.DTOs;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductRequest {
    @NotBlank
    private String nombre;
    
    private String descripcion;
    
    @Positive
    private double precio;
    
    @PositiveOrZero
    private int stock;
    
    @NotBlank
    private String categoria;
}
