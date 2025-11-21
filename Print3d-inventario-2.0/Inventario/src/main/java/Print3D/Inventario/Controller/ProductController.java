package Print3D.Inventario.Controller;

import Print3D.Inventario.DTOs.ProductRequest;
import Print3D.Inventario.DTOs.ProductResponse;
import Print3D.Inventario.Service.ProductService;
import Print3D.Inventario.model.Producto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("")
    public ResponseEntity<Producto> CreateUser(@RequestBody Producto producto){
        
        if( producto.getNombre() == null || producto.getNombre().isEmpty() ||
            producto.getPrecio() == 0 || producto.getPrecio() < 0 ||
            producto.getDescripcion() == null || producto.getDescripcion().isEmpty() ||
            producto.getCategoria() == null || producto.getCategoria().isEmpty() ||
            producto.getStock() == 0 || producto.getStock() < 0){
                return ResponseEntity.badRequest().build();
            }

        Producto productoCreado = productService.crearProducto(producto);

        return new ResponseEntity<>(productoCreado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ProductResponse obtenerProducto(@PathVariable int id) {
        return productService.findById(id);
    }

    @GetMapping
    public List<ProductResponse> listarProductos() {
        return productService.obtenerTodosLosProductos();
    }

    @GetMapping("/categoria/{categoria}")
    public List<ProductResponse> buscarPorCategoria(@PathVariable String categoria) {
        return productService.buscarPorCategoria(categoria);
    }

    @PutMapping("/{id}")
    public ProductResponse actualizarProducto(
            @PathVariable int id,
            @Valid @RequestBody ProductRequest request) {
        return productService.actualizarProducto(id, request);
    }

    @PatchMapping("/{id}/stock")
    public ProductResponse actualizarStock(
            @PathVariable int id,
            @RequestParam int cantidad) {
        return productService.actualizarStock(id, cantidad);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarProducto(
            @PathVariable int id) {
        productService.eliminarProducto(id);
    }
}