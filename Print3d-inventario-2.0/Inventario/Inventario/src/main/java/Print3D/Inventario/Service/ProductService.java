package Print3D.Inventario.Service;

import Print3D.Inventario.DTOs.ProductRequest;
import Print3D.Inventario.DTOs.ProductResponse;
import Print3D.Inventario.model.Producto;
import Print3D.Inventario.Repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductoRepository productoRepository;

    public ProductService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Transactional
    public ProductResponse crearProducto(ProductRequest request, UUID creadorId) {
        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());
        producto.setCategoria(request.getCategoria());
        producto.setCreadorId(creadorId);

        Producto saved = productoRepository.save(producto);
        return mapToResponse(saved);
    }

    public List<ProductResponse> buscarPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ProductResponse mapToResponse(Producto producto) {
        ProductResponse response = new ProductResponse();
        response.setId(producto.getId());
        response.setNombre(producto.getNombre());
        response.setDescripcion(producto.getDescripcion());
        response.setPrecio(producto.getPrecio());
        response.setStock(producto.getStock());
        response.setCategoria(producto.getCategoria());
        return response;
    }
}