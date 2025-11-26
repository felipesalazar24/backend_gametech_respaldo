package Print3D.Carrito.service;

import Print3D.Carrito.DTO.ProductoDTO;
import Print3D.Carrito.model.CarritoItem;
import Print3D.Carrito.repository.CarritoItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CarritoService {

    private final CarritoItemRepository carritoItemRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public CarritoService(CarritoItemRepository carritoItemRepository) {
        this.carritoItemRepository = carritoItemRepository;
    }

    private ProductoDTO obtenerProductoDesdeMicroservicio(int productId) {
        try {
            return restTemplate.getForObject(
                    "http://localhost:8081/productos/id/" + productId, 
                    ProductoDTO.class
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Producto no encontrado en microservicio Productos"
            );
        }
    }

    public List<CarritoItem> obtenerCarrito(int userId) {
        return carritoItemRepository.findByUserId(userId);
    }

    @Transactional
    public CarritoItem agregarProducto(int userId, int productId, int cantidad) {

        if (cantidad <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cantidad inválida");
        }

        ProductoDTO producto = obtenerProductoDesdeMicroservicio(productId);

        CarritoItem existente =
                carritoItemRepository.findByUserIdAndProductId(userId, productId);

        if (existente != null) {
            existente.setCantidad(existente.getCantidad() + cantidad);
            return carritoItemRepository.save(existente);
        }

        CarritoItem nuevo = new CarritoItem(
                userId,
                productId,
                cantidad,
                producto.getPrecio()
        );

        return carritoItemRepository.save(nuevo);
    }

    @Transactional
    public void eliminarProducto(int userId, int productId) {

        CarritoItem item =
                carritoItemRepository.findByUserIdAndProductId(userId, productId);

        if (item == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no existe en carrito");
        }

        carritoItemRepository.delete(item);
    }

    @Transactional
    public void vaciarCarrito(int userId) {
        List<CarritoItem> items = carritoItemRepository.findByUserId(userId);
        carritoItemRepository.deleteAll(items);
    }

    public double calcularTotal(int userId) {
        return carritoItemRepository.findByUserId(userId)
                .stream()
                .mapToDouble(i -> i.getPrecioUnitario() * i.getCantidad())
                .sum();
    }
}
