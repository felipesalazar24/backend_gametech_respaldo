package Print3D.test.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", length = 255, unique = false, nullable = false)
    private String nombre;

    @Column(name = "email", length = 255, unique = true, nullable = false)
    private String email;

    @Column(name = "contrasenia", length = 255, unique = false, nullable = false)
    private String contrasenia;

    @Column(name = "telefono", length =  255, unique =  false, nullable = true)
    private int telefono;

    @Column(name = "region", length = 255, unique = false, nullable = false)
    private String region;

    @Column(name = "comuna", length = 255, unique = false, nullable = false)
    private String comuna;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDate fechaCreacion;

    @Column(name = "rol", length = 255, unique = false, nullable = false, columnDefinition = "varchar(255) default 'cliente'")
    private String rol;

}
