package Print3D.Carrito.repository;

import Print3D.Carrito.model.CarritoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarritoItemRepository extends JpaRepository<CarritoItem, Integer> {

    List<CarritoItem> findByUserId(int userId);

    CarritoItem findByUserIdAndProductId(int userId, Integer productId);
}
