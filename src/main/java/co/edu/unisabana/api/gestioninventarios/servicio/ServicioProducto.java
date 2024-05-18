package co.edu.unisabana.api.gestioninventarios.servicio;

import co.edu.unisabana.api.gestioninventarios.dto.ProductoDTO;

import java.util.List;

public interface ServicioProducto {

    ProductoDTO agregarProducto(ProductoDTO productoDTO);

    ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO);

    void eliminarProducto(Long id);

    ProductoDTO obtenerProductoPorId(Long id);

    List<ProductoDTO> obtenerProductosPorCategoria(Long categoriaId);

    int consultarStockDisponible(Long id);

    List<ProductoDTO> obtenerTodosLosProductos();
}
