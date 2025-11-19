package Print3D.Inventario.Repository;

import Print3D.Inventario.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface ProductoRepository extends JpaRepository<Producto, UUID> {

    List<Producto> findByCategoria(String categoria);

    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(concat('%', :nombre,'%'))")
    List<Producto> buscarPorNombre(@Param("nombre") String nombre);
}
