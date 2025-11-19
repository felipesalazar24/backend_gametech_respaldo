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

    // Crear un nuevo producto
    @Transactional
    public ProductResponse crearProducto(ProductRequest request, UUID creadorId) {
        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());
        producto.setCategoria(request.getCategoria());
        producto.setCreadorId(creadorId);

        Producto productoGuardado = productoRepository.save(producto);
        return mapToResponse(productoGuardado);
    }

    // Obtener producto por ID
    public ProductResponse obtenerProductoPorId(UUID id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        return mapToResponse(producto);
    }

    // Listar todos los productos
    public List<ProductResponse> obtenerTodosLosProductos() {
        return productoRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Buscar productos por categoría
    public List<ProductResponse> buscarPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Actualizar un producto existente
    @Transactional
    public ProductResponse actualizarProducto(UUID id, ProductRequest request, UUID creadorId) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Verificar que el creador sea el dueño del producto
        if (!producto.getCreadorId().equals(creadorId)) {
            throw new RuntimeException("No autorizado para modificar este producto");
        }

        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());
        producto.setCategoria(request.getCategoria());

        Producto actualizado = productoRepository.save(producto);
        return mapToResponse(actualizado);
    }

    // Ajustar stock (aumentar o disminuir)
    @Transactional
    public ProductResponse actualizarStock(UUID id, int cantidad, UUID creadorId) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (!producto.getCreadorId().equals(creadorId)) {
            throw new RuntimeException("No autorizado para modificar este producto");
        }

        int nuevoStock = producto.getStock() + cantidad;
        if (nuevoStock < 0) {
            throw new RuntimeException("Stock no puede ser negativo");
        }

        producto.setStock(nuevoStock);
        Producto actualizado = productoRepository.save(producto);
        return mapToResponse(actualizado);
    }

    // Eliminar un producto
    @Transactional
    public void eliminarProducto(UUID id, UUID creadorId) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (!producto.getCreadorId().equals(creadorId)) {
            throw new RuntimeException("No autorizado para eliminar este producto");
        }

        productoRepository.delete(producto);
    }

    // Mapear Entidad → DTO (Producto → ProductResponse)
    private ProductResponse mapToResponse(Producto producto) {
        return ProductResponse.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .categoria(producto.getCategoria())
                .build();
    }
}