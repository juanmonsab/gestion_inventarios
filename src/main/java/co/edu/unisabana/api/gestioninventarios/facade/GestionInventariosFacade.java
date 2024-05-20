package co.edu.unisabana.api.gestioninventarios.facade;

import co.edu.unisabana.api.gestioninventarios.dto.CategoriaDTO;
import co.edu.unisabana.api.gestioninventarios.dto.ProductoDTO;

import java.util.List;

public interface GestionInventariosFacade {

    List<CategoriaDTO> obtenerTodasLasCategorias();
    CategoriaDTO agregarCategoria(CategoriaDTO categoriaDTO);
    void eliminarCategoria(Long id);
    ProductoDTO agregarProducto(ProductoDTO productoDTO);
    ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO);
    void eliminarProducto(Long id);
    ProductoDTO obtenerProductoPorId(Long id);
    List<ProductoDTO> obtenerProductosPorCategoria(Long categoriaId);
    List<ProductoDTO> obtenerTodosLosProductos();
    int consultarStockDisponible(Long id);
}
