package Print3D.Carrito.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "carrito_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private Integer productId;
    private int cantidad;
    private double precioUnitario;
    private  LocalDate fechaCompra;

    public CarritoItem(Integer userId, Integer productId, int cantidad, double precioUnitario) {
        this.userId = userId;
        this.productId = productId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
}
