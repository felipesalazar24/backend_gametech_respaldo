package Print3D.Carrito.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    
    private Integer id;
    private String nombre;
    private int cantidad;
    private double precio;

}
