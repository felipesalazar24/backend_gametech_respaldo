package Print3D.Inventario.Repository;

import Print3D.Inventario.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query(value = "SELECT * FROM productos WHERE categoria = :categoria", nativeQuery = true)
    List<Producto> findByCategoria(@Param("categoria") String categoria);


    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Producto> buscarPorNombre(@Param("nombre") String nombre);

    @Query("SELECT p.stock FROM Producto p WHERE p.id = :productoId")
    Integer verificarStock(@Param("productoId") int productoId);
    
}
