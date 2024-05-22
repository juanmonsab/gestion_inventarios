package co.edu.unisabana.api.gestioninventarios.integration;

import co.edu.unisabana.api.gestioninventarios.dto.CategoriaDTO;
import co.edu.unisabana.api.gestioninventarios.dto.ProductoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ProductoControladorTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAgregarProducto() {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setNombre("Tecnologia");

        ResponseEntity<CategoriaDTO> responseAgregarCategoria =
                restTemplate.postForEntity("/categorias/agregar", categoriaDTO, CategoriaDTO.class);

        Assertions.assertNotNull(responseAgregarCategoria.getBody());
        Assertions.assertEquals(HttpStatus.CREATED, responseAgregarCategoria.getStatusCode());

        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre("Apple");
        productoDTO.setDescripcion("Mackbook Pro M3");
        productoDTO.setPrecio(10000000);
        productoDTO.setCantidad(50);
        productoDTO.setCategoria(responseAgregarCategoria.getBody());

        ResponseEntity<ProductoDTO> responseAgregarProducto =
                restTemplate.postForEntity("/productos/agregar", productoDTO, ProductoDTO.class);

        Assertions.assertNotNull(responseAgregarProducto.getBody());
        Assertions.assertEquals(HttpStatus.OK, responseAgregarProducto.getStatusCode());
        Assertions.assertEquals("Apple", responseAgregarProducto.getBody().getNombre());
    }

    @Test
    public void testActualizarProducto() {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setNombre("licores");

        ResponseEntity<CategoriaDTO> responseAgregarCategoria =
                restTemplate.postForEntity("/categorias/agregar", categoriaDTO, CategoriaDTO.class);

        Assertions.assertNotNull(responseAgregarCategoria.getBody());
        Assertions.assertEquals(HttpStatus.CREATED, responseAgregarCategoria.getStatusCode());

        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre("Whiski");
        productoDTO.setDescripcion("Black Label");
        productoDTO.setPrecio(100000);
        productoDTO.setCantidad(100);
        productoDTO.setCategoria(responseAgregarCategoria.getBody());

        ResponseEntity<ProductoDTO> responseAgregarProducto =
                restTemplate.postForEntity("/productos/agregar", productoDTO, ProductoDTO.class);

        Assertions.assertNotNull(responseAgregarProducto.getBody());
        Assertions.assertEquals(HttpStatus.OK, responseAgregarProducto.getStatusCode());

        ProductoDTO productoActualizadoDTO = new ProductoDTO();
        productoActualizadoDTO.setNombre("Whisky");
        productoActualizadoDTO.setDescripcion("Blue Label");
        productoActualizadoDTO.setPrecio(950000);
        productoActualizadoDTO.setCantidad(25);
        productoActualizadoDTO.setCategoria(responseAgregarCategoria.getBody());

        ResponseEntity<ProductoDTO> responseActualizarProducto =
                restTemplate.exchange("/productos/" + responseAgregarProducto.getBody().getId(),
                        HttpMethod.PUT, new HttpEntity<>(productoActualizadoDTO), ProductoDTO.class);

        Assertions.assertNotNull(responseActualizarProducto.getBody());
        Assertions.assertEquals(HttpStatus.OK, responseActualizarProducto.getStatusCode());
        Assertions.assertEquals("Whisky", responseActualizarProducto.getBody().getNombre());
    }

    @Test
    public void testObtenerProductoPorId() {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setNombre("Tecnologia");

        ResponseEntity<CategoriaDTO> responseAgregarCategoria =
                restTemplate.postForEntity("/categorias/agregar", categoriaDTO, CategoriaDTO.class);

        Assertions.assertNotNull(responseAgregarCategoria.getBody());
        Assertions.assertEquals(HttpStatus.CREATED, responseAgregarCategoria.getStatusCode());

        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre("Apple");
        productoDTO.setDescripcion("Mackbook Pro M3");
        productoDTO.setPrecio(10000000);
        productoDTO.setCantidad(50);
        productoDTO.setCategoria(responseAgregarCategoria.getBody());

        ResponseEntity<ProductoDTO> responseAgregarProducto =
                restTemplate.postForEntity("/productos/agregar", productoDTO, ProductoDTO.class);

        Assertions.assertNotNull(responseAgregarProducto.getBody());
        Assertions.assertEquals(HttpStatus.OK, responseAgregarProducto.getStatusCode());

        Long productId = responseAgregarProducto.getBody().getId();
        ResponseEntity<ProductoDTO> responseObtenerProducto =
                restTemplate.getForEntity("/productos/" + productId, ProductoDTO.class);

        Assertions.assertNotNull(responseObtenerProducto.getBody());
        Assertions.assertEquals(HttpStatus.OK, responseObtenerProducto.getStatusCode());
        Assertions.assertEquals("Apple", responseObtenerProducto.getBody().getNombre());
    }
    @Test
    public void testConsultarStockDisponible() {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setNombre("Tecnologia");

        ResponseEntity<CategoriaDTO> responseAgregarCategoria =
                restTemplate.postForEntity("/categorias/agregar", categoriaDTO, CategoriaDTO.class);

        Assertions.assertNotNull(responseAgregarCategoria.getBody());
        Assertions.assertEquals(HttpStatus.CREATED, responseAgregarCategoria.getStatusCode());

        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre("Apple");
        productoDTO.setDescripcion("Mackbook Pro M3");
        productoDTO.setPrecio(10000000);
        productoDTO.setCantidad(50);
        productoDTO.setCategoria(responseAgregarCategoria.getBody());

        ResponseEntity<ProductoDTO> responseAgregarProducto =
                restTemplate.postForEntity("/productos/agregar", productoDTO, ProductoDTO.class);

        Assertions.assertNotNull(responseAgregarProducto.getBody());
        Assertions.assertEquals(HttpStatus.OK, responseAgregarProducto.getStatusCode());
        Assertions.assertEquals("Apple", responseAgregarProducto.getBody().getNombre());

        ResponseEntity<Integer> responseStockDisponible =
                restTemplate.getForEntity("/productos/" + responseAgregarProducto.getBody().getId() + "/stock", Integer.class);

        Assertions.assertNotNull(responseStockDisponible.getBody());
        Assertions.assertEquals(HttpStatus.OK, responseStockDisponible.getStatusCode());
        Assertions.assertEquals(50, responseStockDisponible.getBody().intValue());
    }
    @Test
    public void testObtenerProductosPorCategoria() {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setNombre("Tecnologia");

        ResponseEntity<CategoriaDTO> responseAgregarCategoria =
                restTemplate.postForEntity("/categorias/agregar", categoriaDTO, CategoriaDTO.class);

        Assertions.assertNotNull(responseAgregarCategoria.getBody());
        Assertions.assertEquals(HttpStatus.CREATED, responseAgregarCategoria.getStatusCode());

        ProductoDTO producto1 = new ProductoDTO();
        producto1.setNombre("Apple");
        producto1.setDescripcion("Macbook Pro M3");
        producto1.setPrecio(10000000);
        producto1.setCantidad(50);
        producto1.setCategoria(responseAgregarCategoria.getBody());

        ProductoDTO producto2 = new ProductoDTO();
        producto2.setNombre("Apple");
        producto2.setDescripcion("iPad Air 256 GB");
        producto2.setPrecio(3000000);
        producto2.setCantidad(75);
        producto2.setCategoria(responseAgregarCategoria.getBody());

        restTemplate.postForEntity("/productos/agregar", producto1, ProductoDTO.class);
        restTemplate.postForEntity("/productos/agregar", producto2, ProductoDTO.class);

        ResponseEntity<ProductoDTO[]> responseProductosPorCategoria =
                restTemplate.getForEntity("/productos/categoria/" + responseAgregarCategoria.getBody().getId(), ProductoDTO[].class);

        Assertions.assertNotNull(responseProductosPorCategoria.getBody());
        Assertions.assertEquals(HttpStatus.OK, responseProductosPorCategoria.getStatusCode());
        Assertions.assertEquals(2, responseProductosPorCategoria.getBody().length);
    }
    @Test
    public void testEliminarProducto() {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setNombre("Vehiculos");

        ResponseEntity<CategoriaDTO> responseAgregarCategoria =
                restTemplate.postForEntity("/categorias/agregar", categoriaDTO, CategoriaDTO.class);

        Assertions.assertNotNull(responseAgregarCategoria.getBody());
        Assertions.assertEquals(HttpStatus.CREATED, responseAgregarCategoria.getStatusCode());

        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre("BMW");
        productoDTO.setDescripcion("320i");
        productoDTO.setPrecio(85000000);
        productoDTO.setCantidad(3);
        productoDTO.setCategoria(responseAgregarCategoria.getBody());

        ResponseEntity<ProductoDTO> responseAgregarProducto =
                restTemplate.postForEntity("/productos/agregar", productoDTO, ProductoDTO.class);

        Assertions.assertNotNull(responseAgregarProducto.getBody());
        Assertions.assertEquals(HttpStatus.OK, responseAgregarProducto.getStatusCode());

        restTemplate.delete("/productos/" + responseAgregarProducto.getBody().getId());

        waitForProductToBeDeleted(responseAgregarProducto.getBody().getId());

        ResponseEntity<ProductoDTO[]> responseProductosDespuesEliminar =
                restTemplate.getForEntity("/productos/obtener", ProductoDTO[].class);

        ProductoDTO[] productosDespuesEliminar = responseProductosDespuesEliminar.getBody();
        Assertions.assertNotNull(productosDespuesEliminar);
        boolean foundDeletedProduct = false;
        for (ProductoDTO producto : productosDespuesEliminar) {
            if (producto.getId().equals(responseAgregarProducto.getBody().getId())) {
                foundDeletedProduct = true;
                break;
            }
        }
        Assertions.assertFalse(foundDeletedProduct, "Se encontró el producto eliminado en la lista después de la eliminación");
    }

    private void waitForProductToBeDeleted(Long productId) {
        int maxAttempts = 10;
        int attempts = 0;
        boolean productDeleted = false;
        while (!productDeleted && attempts < maxAttempts) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ResponseEntity<ProductoDTO> responseProducto =
                    restTemplate.getForEntity("/productos/" + productId, ProductoDTO.class);
            if (responseProducto.getStatusCode() == HttpStatus.NOT_FOUND) {
                productDeleted = true;
            }
            attempts++;
        }
    }
}








