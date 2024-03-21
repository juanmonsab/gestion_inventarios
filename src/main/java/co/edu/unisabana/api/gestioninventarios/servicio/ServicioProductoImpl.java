package co.edu.unisabana.api.gestioninventarios.servicio;

import co.edu.unisabana.api.gestioninventarios.dto.CategoriaDTO;
import co.edu.unisabana.api.gestioninventarios.dto.ProductoDTO;
import co.edu.unisabana.api.gestioninventarios.modelo.Categoria;
import co.edu.unisabana.api.gestioninventarios.modelo.Producto;
import co.edu.unisabana.api.gestioninventarios.repositorio.CategoriaRepositorio;
import co.edu.unisabana.api.gestioninventarios.repositorio.ProductoRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicioProductoImpl implements ServicioProducto {

    private final ProductoRepositorio productoRepository;
    private final CategoriaRepositorio categoriaRepository;

    public ServicioProductoImpl(ProductoRepositorio productoRepository, CategoriaRepositorio categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public ProductoDTO agregarProducto(ProductoDTO productoDTO) {
        Producto producto = convertirAProducto(productoDTO);
        return convertirAProductoDTO(productoRepository.save(producto));
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        Producto productoActualizado = convertirAProducto(productoDTO);
        BeanUtils.copyProperties(productoActualizado, productoExistente, "id");
        return convertirAProductoDTO(productoRepository.save(productoExistente));
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public ProductoDTO obtenerProductoPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        return convertirAProductoDTO(producto);
    }

    @Override
    public List<ProductoDTO> obtenerProductosPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoria_Id(categoriaId).stream()
                .map(this::convertirAProductoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public int consultarStockDisponible(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        return producto.getCantidad();
    }

    public ProductoDTO convertirAProductoDTO(Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO();
        BeanUtils.copyProperties(producto, productoDTO, "categoria");
        if (producto.getCategoria() != null) {
            productoDTO.setCategoria(convertirACategoriaDTO(producto.getCategoria()));
        }
        return productoDTO;
    }

    public Producto convertirAProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        BeanUtils.copyProperties(productoDTO, producto, "categoria");
        if (productoDTO.getCategoria() != null && productoDTO.getCategoria().getId() != null) {
            Optional<Categoria> categoriaOptional = categoriaRepository.findById(productoDTO.getCategoria().getId());
            categoriaOptional.ifPresent(producto::setCategoria);
        }
        return producto;
    }

    public CategoriaDTO convertirACategoriaDTO(Categoria categoria) {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(categoria.getId());
        categoriaDTO.setNombre(categoria.getNombre());
        return categoriaDTO;
    }
}