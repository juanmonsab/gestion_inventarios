package co.edu.unisabana.api.gestioninventarios.controlador;

import co.edu.unisabana.api.gestioninventarios.dto.ProductoDTO;
import co.edu.unisabana.api.gestioninventarios.facade.GestionInventariosFacade;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoControlador {

    private final GestionInventariosFacade gestionInventariosFacade;

    public ProductoControlador(GestionInventariosFacade gestionInventariosFacade) {
        this.gestionInventariosFacade = gestionInventariosFacade;
    }

    @PostMapping("/agregar")
    public ProductoDTO agregarProducto(@RequestBody ProductoDTO productoDTO) {
        return gestionInventariosFacade.agregarProducto(productoDTO);
    }

    @PutMapping("/{id}")
    public ProductoDTO actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        return gestionInventariosFacade.actualizarProducto(id, productoDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        gestionInventariosFacade.eliminarProducto(id);
    }

    @GetMapping("/{id}")
    public ProductoDTO obtenerProductoPorId(@PathVariable Long id) {
        return gestionInventariosFacade.obtenerProductoPorId(id);
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<ProductoDTO> obtenerProductosPorCategoria(@PathVariable Long categoriaId) {
        return gestionInventariosFacade.obtenerProductosPorCategoria(categoriaId);
    }

    @GetMapping("/obtener") // Endpoint para obtener todos los productos
    public List<ProductoDTO> obtenerTodosLosProductos() {
        return gestionInventariosFacade.obtenerTodosLosProductos();
    }

    @GetMapping("/{id}/stock")
    public int consultarStockDisponible(@PathVariable Long id) {
        return gestionInventariosFacade.consultarStockDisponible(id);
    }
}

