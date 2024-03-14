package co.edu.unisabana.api.gestioninventarios.controlador;

import co.edu.unisabana.api.gestioninventarios.dto.ProductoDTO;
import co.edu.unisabana.api.gestioninventarios.servicio.ServicioProducto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoControlador {

    private final ServicioProducto servicioProducto;

    public ProductoControlador(ServicioProducto servicioProducto) {
        this.servicioProducto = servicioProducto;
    }

    @PostMapping
    public ProductoDTO agregarProducto(@RequestBody ProductoDTO productoDTO) {
        return servicioProducto.agregarProducto(productoDTO);
    }

    @PutMapping("/{id}")
    public ProductoDTO actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        return servicioProducto.actualizarProducto(id, productoDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        servicioProducto.eliminarProducto(id);
    }

    @GetMapping("/{id}")
    public ProductoDTO obtenerProductoPorId(@PathVariable Long id) {
        return servicioProducto.obtenerProductoPorId(id);
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<ProductoDTO> obtenerProductosPorCategoria(@PathVariable Long categoriaId) {
        return servicioProducto.obtenerProductosPorCategoria(categoriaId);
    }

    @GetMapping("/{id}/stock")
    public int consultarStockDisponible(@PathVariable Long id) {
        return servicioProducto.consultarStockDisponible(id);
    }
}