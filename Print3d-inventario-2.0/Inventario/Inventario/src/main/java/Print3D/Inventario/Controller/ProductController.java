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

    private final ProductService productoService;

    public ProductController(ProductService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse crearProducto(
            @Valid @RequestBody ProductRequest request,
            @AuthenticationPrincipal Jwt jwt){
            //AuthenticationPrincipal Jwt jwt) {
        
        UUID creadorId = UUID.fromString(jwt.getSubject()); // Obtiene el ID del usuario del JWT
        return productoService.crearProducto(request, creadorId);
    }

    @GetMapping("/categoria/{categoria}")
    public List<ProductResponse> buscarPorCategoria(@PathVariable String categoria) {
        return productoService.buscarPorCategoria(categoria);
    }
}