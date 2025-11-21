package Print3D.test.repository;

import Print3D.test.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

@Query(value = "select u.id, u.rut, u.nombre, u.email, u.telefono, u.contrasenia, u.fecha_creacion, u.rol from usuarios u where u.rol = 'cliente'", nativeQuery = true)
List<Usuario> findCliente();

}
