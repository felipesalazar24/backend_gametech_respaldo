package Print3D.Inventario.Controller;

import Print3D.Inventario.DTOs.ProductRequest;
import Print3D.Inventario.DTOs.ProductResponse;
import Print3D.Inventario.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/productos")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse crearProducto(
            @Valid @RequestBody ProductRequest request,
            @AuthenticationPrincipal Jwt jwt) {
        
        UUID creadorId = UUID.fromString(jwt.getSubject());
        return productService.crearProducto(request, creadorId);
    }

    @GetMapping("/{id}")
    public ProductResponse obtenerProducto(@PathVariable UUID id) {
        return productService.obtenerProductoPorId(id);
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
            @PathVariable UUID id,
            @Valid @RequestBody ProductRequest request,
            @AuthenticationPrincipal Jwt jwt) {
        
        UUID creadorId = UUID.fromString(jwt.getSubject());
        return productService.actualizarProducto(id, request, creadorId);
    }

    @PatchMapping("/{id}/stock")
    public ProductResponse actualizarStock(
            @PathVariable UUID id,
            @RequestParam int cantidad,
            @AuthenticationPrincipal Jwt jwt) {
        
        UUID creadorId = UUID.fromString(jwt.getSubject());
        return productService.actualizarStock(id, cantidad, creadorId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarProducto(
            @PathVariable UUID id,
            @AuthenticationPrincipal Jwt jwt) {
        
        UUID creadorId = UUID.fromString(jwt.getSubject());
        productService.eliminarProducto(id, creadorId);
    }
}