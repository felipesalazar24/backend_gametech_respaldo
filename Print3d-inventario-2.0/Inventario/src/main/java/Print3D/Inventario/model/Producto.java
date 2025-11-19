package Print3D.Inventario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "productos")
@Data // Genera getters, setters, toString, equals y hashCode (Lombok)
@Builder // Patrón builder para creación flexible de objetos
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @Column(columnDefinition = "TEXT")
    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String descripcion;

    @Column(name = "precio", nullable = false, columnDefinition = "DECIMAL(10,2)")  // Definición explícita
    @DecimalMin(value = "0.01", message = "El precio mínimo es 0.01")
    @NotNull(message = "El precio es obligatorio")
    private Double precio;  // Cambia de Double a BigDecimal

    @Column(nullable = false)
    @Min(value = 0, message = "El stock no puede ser negativo")
    @NotNull(message = "El stock es obligatorio")
    private Integer stock;

    @Column(name = "creador_id", nullable = false)
    private UUID creadorId; // Relación con el usuario creador (no es una FK)

    @Column(nullable = false, length = 50)
    @NotBlank(message = "La categoría es obligatoria")
    @Size(max = 50, message = "La categoría no puede exceder 50 caracteres")
    private String categoria;

    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    // Método de negocio para reducir stock
    public void reducirStock(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }
        if (this.stock < cantidad) {
            throw new IllegalStateException("Stock insuficiente");
        }
        this.stock -= cantidad;
    }

    // Método de negocio para aumentar stock
    public void aumentarStock(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }
        this.stock += cantidad;
    }
}
