package co.edu.unisabana.api.gestioninventarios.integration;

import co.edu.unisabana.api.gestioninventarios.dto.ProductoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductoControladorTest extends AbstractTest{

    String path ="/productos";

    @Test
    void testAgregarProducto() {
        String actualizapath = path + "/agregar";
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre("Prueba");
        productoDTO.setPrecio(4000);
        ResponseEntity<ProductoDTO> response = restTemplate.postForEntity(actualizapath,productoDTO,ProductoDTO.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ProductoDTO productoCreado = response.getBody();
        assertNotNull(productoCreado);
        assertNotNull(productoCreado.getId());
        assertEquals("Prueba",productoCreado.getNombre());
        assertEquals(4000,productoCreado.getPrecio());
    }
    @Test
    void testActualizarProducto() {
        String actualizapath = path + "/{id}";
        ProductoDTO productoDTO= new ProductoDTO();
        productoDTO.setNombre("Producto Actualizado");
        productoDTO.setPrecio(100000);
        ResponseEntity<ProductoDTO> response = restTemplate.postForEntity(path,productoDTO, ProductoDTO.class);
        ProductoDTO productoCreado = response.getBody();
        Long productoId = productoCreado.getId();
        assertNotNull(productoId);
        restTemplate.put(actualizapath,productoDTO,productoId);
        ResponseEntity<ProductoDTO> actualizaResponse = restTemplate.getForEntity(actualizapath, ProductoDTO.class,productoId);
        ProductoDTO productoActualizado = actualizaResponse.getBody();
        assertNotNull(productoActualizado);
        assertEquals("Producto Actualizado",productoActualizado.getNombre());
        assertEquals(100000,productoActualizado.getPrecio());
    }
    @Test
    void testEliminarProducto() {
        String agregapath = path + "/agregar";
        String deletepath = path + "/{id}";
        ResponseEntity<ProductoDTO> response = restTemplate.postForEntity(agregapath,new ProductoDTO(), ProductoDTO.class);
        ProductoDTO prodcutoCreado = response.getBody();
        Long productoId = prodcutoCreado.getId();
        assertNotNull(productoId);
        restTemplate.delete(deletepath,productoId);
        ResponseEntity<ProductoDTO> deleteresponse = restTemplate.getForEntity(deletepath, ProductoDTO.class,productoId);
        assertEquals(HttpStatus.NOT_FOUND,deleteresponse.getStatusCode());
    }
    @Test
    void testObtneterProductoPorId() {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre("Coca-Cola");
        productoDTO.setPrecio(4000);
        ResponseEntity<ProductoDTO> response = restTemplate.postForEntity(path,productoDTO, ProductoDTO.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ProductoDTO productoCreado = response.getBody();
        assertNotNull(productoCreado);
        assertNotNull(productoCreado.getId());
        assertEquals("Coca-Cola",productoCreado.getNombre());
        assertEquals(4000,productoCreado.getPrecio());
        Long productoId = productoCreado.getId();
        String getpath = path + "/" + productoId;
        ResponseEntity<ProductoDTO> getresponse = restTemplate.getForEntity(getpath,ProductoDTO.class);
        assertEquals(HttpStatus.OK,getresponse.getStatusCode());
        ProductoDTO productoObtenido = getresponse.getBody();
        assertNotNull(productoObtenido);
        assertEquals(productoId,productoObtenido.getId());
        assertEquals("Cola-Cola",productoObtenido.getNombre());
        assertEquals(4000,productoObtenido.getPrecio());
    }
    @Test
    void testConsultarStockDisponible(){
        String postpath = path + "/agregar";
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre("Coca-cola");
        productoDTO.setPrecio(4000);
        ResponseEntity<ProductoDTO> response = restTemplate.postForEntity(postpath,productoDTO, ProductoDTO.class);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        ProductoDTO productoCreado = response.getBody();
        assertNotNull(productoCreado);
        assertNotNull(productoCreado.getId());
        assertEquals("Cola-Cola",productoCreado.getNombre());
        Long productoId = productoCreado.getId();
        String stockpath = path + "/" + productoId + "/stock";
        ResponseEntity<Integer> stockresponse = restTemplate.getForEntity(stockpath,Integer.class);
        assertEquals(HttpStatus.OK,stockresponse.getStatusCode());
        Integer stock = stockresponse.getBody();
        assertNotNull(stock);
    }
}
