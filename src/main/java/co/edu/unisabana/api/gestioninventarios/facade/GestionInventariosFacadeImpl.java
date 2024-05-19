package co.edu.unisabana.api.gestioninventarios.facade;

import co.edu.unisabana.api.gestioninventarios.dto.CategoriaDTO;
import co.edu.unisabana.api.gestioninventarios.dto.ProductoDTO;
import co.edu.unisabana.api.gestioninventarios.servicio.ServicioCategoria;
import co.edu.unisabana.api.gestioninventarios.servicio.ServicioProducto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GestionInventariosFacadeImpl implements GestionInventariosFacade {

    private final ServicioCategoria servicioCategoria;
    private final ServicioProducto servicioProducto;

    public GestionInventariosFacadeImpl(ServicioCategoria servicioCategoria, ServicioProducto servicioProducto) {
        this.servicioCategoria = servicioCategoria;
        this.servicioProducto = servicioProducto;
    }

    @Override
    public List<CategoriaDTO> obtenerTodasLasCategorias() {
        return servicioCategoria.obtenerTodasLasCategorias();
    }

    @Override
    public CategoriaDTO agregarCategoria(CategoriaDTO categoriaDTO) {
        return servicioCategoria.agregarCategoria(categoriaDTO);
    }

    @Override
    public void eliminarCategoria(Long id) {
        servicioCategoria.eliminarCategoria(id);
    }

    @Override
    public ProductoDTO agregarProducto(ProductoDTO productoDTO) {
        return servicioProducto.agregarProducto(productoDTO);
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO) {
        return servicioProducto.actualizarProducto(id, productoDTO);
    }

    @Override
    public void eliminarProducto(Long id) {
        servicioProducto.eliminarProducto(id);
    }

    @Override
    public ProductoDTO obtenerProductoPorId(Long id) {
        return servicioProducto.obtenerProductoPorId(id);
    }

    @Override
    public List<ProductoDTO> obtenerProductosPorCategoria(Long categoriaId) {
        return servicioProducto.obtenerProductosPorCategoria(categoriaId);
    }

    @Override
    public List<ProductoDTO> obtenerTodosLosProductos() {
        return servicioProducto.obtenerTodosLosProductos();
    }

    @Override
    public int consultarStockDisponible(Long id) {
        return servicioProducto.consultarStockDisponible(id);
    }
}
