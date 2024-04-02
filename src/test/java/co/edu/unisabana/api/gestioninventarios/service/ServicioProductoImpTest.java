package co.edu.unisabana.api.gestioninventarios.service;

import co.edu.unisabana.api.gestioninventarios.dto.CategoriaDTO;
import co.edu.unisabana.api.gestioninventarios.dto.ProductoDTO;
import co.edu.unisabana.api.gestioninventarios.modelo.Categoria;
import co.edu.unisabana.api.gestioninventarios.modelo.Producto;
import co.edu.unisabana.api.gestioninventarios.repositorio.ProductoRepositorio;
import co.edu.unisabana.api.gestioninventarios.servicio.ServicioProductoImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ServicioProductoImpTest {

    @Mock
    ProductoRepositorio repositorio;
    @InjectMocks
    ServicioProductoImpl servicio;

    @Test
    void DadoProductoDTO_CuandoAgregarProducto_EntoncesRetornaProductoAgregado() {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre("Prueba");
        productoDTO.setPrecio(10000);
        productoDTO.setCantidad(10);
        Producto productoGuardado = new Producto();
        productoGuardado.setId(1L);
        productoGuardado.setNombre(productoDTO.getNombre());
        productoGuardado.setPrecio(productoDTO.getPrecio());
        productoGuardado.setCantidad(productoDTO.getCantidad());
        when(repositorio.save(any(Producto.class))).thenReturn(productoGuardado);
        ProductoDTO productoAgregado = servicio.agregarProducto(productoDTO);
        assertNotNull(productoAgregado);
        assertNotNull(productoAgregado.getId());
        assertEquals(productoDTO.getNombre(), productoAgregado.getNombre());
        assertEquals(productoDTO.getCantidad(), productoAgregado.getCantidad());
        verify(repositorio).save(any(Producto.class));
    }
    @Test
    void DadoProductoDTOAndId_CuandoActualizarProducto_EntoncesRetornaProductoActualizado() {
        Long idProducto = 1L;
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre("Producto Actualizado");
        productoDTO.setPrecio(150000);
        productoDTO.setCantidad(20);
        Producto productoExistente = new Producto();
        productoExistente.setId(idProducto);
        when(repositorio.findById(idProducto)).thenReturn(Optional.of(productoExistente));
        when(repositorio.save(any(Producto.class))).thenReturn(productoExistente);
        ProductoDTO productoActualizado = servicio.actualizarProducto(idProducto, productoDTO);
        assertNotNull(productoActualizado);
        assertEquals(idProducto, productoActualizado.getId());
        assertEquals(productoDTO.getNombre(), productoActualizado.getNombre());
        assertEquals(productoDTO.getCantidad(), productoActualizado.getCantidad());
        verify(repositorio).save(any(Producto.class));
    }
    @Test
    void DadoId_CuandoEliminarProducto_EntoncesProductoEliminadoNull() {
        Long idProducto = 1L;
        when(repositorio.findById(idProducto)).thenReturn(Optional.empty());
        servicio.eliminarProducto(idProducto);
        verify(repositorio).deleteById(idProducto);
        assertThrows(RuntimeException.class, () -> servicio.obtenerProductoPorId(idProducto));
    }
    @Test
    void DadoId_CuandoObtenerProductoPorId_EntoncesProductoObtenido() {
        Long idProducto = 1L;
        Producto producto = new Producto();
        producto.setId(idProducto);
        when(repositorio.findById(idProducto)).thenReturn(Optional.of(producto));
        ProductoDTO resultado = servicio.obtenerProductoPorId(idProducto);
        assertNotNull(resultado);
        assertEquals(idProducto, resultado.getId());
    }
    @Test
    void DadoCategoriaId_CuandoObtenerProductosPorCategoria_EntoncesProductosObtenidos () {
        Long idCategoria = 1L;
        List<Producto> productosEntity = new ArrayList<>();
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setCategoria(new Categoria());
        producto.getCategoria().setId(idCategoria);
        productosEntity.add(producto);
        when(repositorio.findByCategoria_Id(anyLong())).thenReturn(productosEntity);
        List<ProductoDTO> productos;
        productos = servicio.obtenerProductosPorCategoria(idCategoria);
        assertNotNull(productos);
        assertFalse(productos.isEmpty());
        assertEquals(idCategoria, productos.get(0).getCategoria().getId());
    }
    @Test
    void DadoId_CuandoConcultarStockDisponible_EntoncesStock () {

        Long idProducto = 1L;
        int stockEsperado = 50;
        Producto producto = new Producto();
        producto.setId(idProducto);
        producto.setCantidad(stockEsperado);
        when(repositorio.findById(idProducto)).thenReturn(Optional.of(producto));
        int stockObtenido = servicio.consultarStockDisponible(idProducto);
        assertEquals(stockEsperado,stockObtenido);
    }
    @Test
    void DadoProducto_CuandoConvertirAProductoDTO_EntoncesDTOConverted ()  {

        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Tequila");
        producto.setPrecio(100000);
        producto.setCantidad(20);
        ProductoDTO productoDTO = servicio.convertirAProductoDTO(producto);
        assertNotNull(productoDTO);
        assertEquals(producto.getId(),productoDTO.getId());
        assertEquals(producto.getNombre(),productoDTO.getNombre());
        assertEquals(producto.getPrecio(),productoDTO.getPrecio());
        assertEquals(producto.getCantidad(),productoDTO.getCantidad());
    }
    @Test
    void DadoProductoDTO_CuandoConvertirAProducto_EntoncesProductoConverted () {

        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(1L);
        productoDTO.setNombre("Guaro");
        productoDTO.setPrecio(28000);
        productoDTO.setCantidad(40);
        Producto producto = servicio.convertirAProducto(productoDTO);
        assertNotNull(producto);
        assertEquals(productoDTO.getId(),producto.getId());
        assertEquals(productoDTO.getNombre(),producto.getNombre());
        assertEquals(productoDTO.getPrecio(),producto.getPrecio());
        assertEquals(productoDTO.getCantidad(),producto.getCantidad());
    }
    @Test
    void DadoCategoria_CaundoConvertirCategoriaDTO_EntoncesRetornaCategoriaDTO() {

        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNombre("Prueba");
        CategoriaDTO categoriaDTO =servicio.convertirACategoriaDTO(categoria);
        assertEquals(categoria.getId(),categoriaDTO.getId());
        assertEquals(categoria.getNombre(),categoriaDTO.getNombre());

    }
}
