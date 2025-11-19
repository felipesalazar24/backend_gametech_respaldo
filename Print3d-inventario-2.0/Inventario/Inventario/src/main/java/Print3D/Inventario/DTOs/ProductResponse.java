package Print3D.Inventario.DTOs;

import lombok.Data;
import java.util.UUID;

@Data
public class ProductResponse {
    private UUID id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private String categoria;
}