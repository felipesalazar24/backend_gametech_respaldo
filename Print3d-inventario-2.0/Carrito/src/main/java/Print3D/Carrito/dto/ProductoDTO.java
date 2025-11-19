package Print3D.Carrito.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data

public class ProductoDTO {
    private String id;
    private String nombre;
    private double precio;
    private int stock;
}

