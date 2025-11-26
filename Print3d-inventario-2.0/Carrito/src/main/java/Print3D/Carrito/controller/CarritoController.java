package Print3D.Carrito.controller;

import Print3D.Carrito.model.CarritoItem;
import Print3D.Carrito.service.CarritoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CarritoItem>> obtenerCarrito(@PathVariable int userId) {
        return ResponseEntity.ok(carritoService.obtenerCarrito(userId));
    }

    @PostMapping("/{userId}/agregar")
    public ResponseEntity<CarritoItem> agregarProducto(
            @PathVariable int userId,
            @RequestParam int productId,
            @RequestParam int cantidad
    ) {
        return ResponseEntity.ok(
                carritoService.agregarProducto(userId, productId, cantidad)
        );
    }

    @DeleteMapping("/{userId}/eliminar")
    public ResponseEntity<String> eliminarProducto(
            @PathVariable int userId,
            @RequestParam int productId
    ) {
        carritoService.eliminarProducto(userId, productId);
        return ResponseEntity.ok("Producto eliminado");
    }

    @DeleteMapping("/{userId}/vaciar")
    public ResponseEntity<String> vaciarCarrito(@PathVariable int userId) {
        carritoService.vaciarCarrito(userId);
        return ResponseEntity.ok("Carrito vaciado");
    }

    @GetMapping("/{userId}/total")
    public ResponseEntity<Double> total(@PathVariable int userId) {
        return ResponseEntity.ok(carritoService.calcularTotal(userId));
    }
}
