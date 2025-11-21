package Print3D.Inventario.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "productos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", length = 255, unique = false, nullable = false)
    private String nombre;

    @Column(name = "precio", length = 255, unique = true, nullable = false)
    private double precio;

    @Column(name = "descripcion", length = 255, unique = false, nullable = false)
    private String descripcion;

    @Column(name = "stock", length =  255, unique =  true, nullable = true)
    private int stock;
    @Column(name = "categoria", length = 255, unique = false, nullable = false)
    private String categoria;
}
