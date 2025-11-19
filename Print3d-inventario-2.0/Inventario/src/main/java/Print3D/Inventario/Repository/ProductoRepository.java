package Print3D.Inventario.Repository;

import Print3D.Inventario.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, UUID> {

    // Consulta básica por categoría
    List<Producto> findByCategoria(String categoria);

    // Consulta personalizada con JPQL para búsqueda por nombre (insensible a mayúsculas)
    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Producto> buscarPorNombre(@Param("nombre") String nombre);

    // Consulta para verificar stock
    @Query("SELECT p.stock FROM Producto p WHERE p.id = :productoId")
    Integer verificarStock(@Param("productoId") UUID productoId);

    // Consulta para productos de un creador específico
    List<Producto> findByCreadorId(UUID creadorId);
}
