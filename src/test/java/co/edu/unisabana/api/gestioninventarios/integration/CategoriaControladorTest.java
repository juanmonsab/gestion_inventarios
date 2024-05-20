package co.edu.unisabana.api.gestioninventarios.integration;

import co.edu.unisabana.api.gestioninventarios.dto.CategoriaDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoriaControladorTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAgregarCategoria() {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setNombre("CategoriaTest");

        ResponseEntity<CategoriaDTO> responseAgregar =
                restTemplate.postForEntity("/categorias/agregar", categoriaDTO, CategoriaDTO.class);

        Assertions.assertNotNull(responseAgregar.getBody());
        Assertions.assertEquals(HttpStatus.CREATED, responseAgregar.getStatusCode());
        Assertions.assertEquals("CategoriaTest", responseAgregar.getBody().getNombre());
    }

    @Test
    public void testObtenerCategorias() {
        ResponseEntity<CategoriaDTO[]> responseObtener =
                restTemplate.getForEntity("/categorias/obtener", CategoriaDTO[].class);

        CategoriaDTO[] categorias = responseObtener.getBody();
        Assertions.assertNotNull(categorias);
        Assertions.assertEquals(HttpStatus.OK, responseObtener.getStatusCode());
    }

    @Test
    public void testEliminarCategoria() {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setNombre("Categoría a Eliminar");

        ResponseEntity<CategoriaDTO> responseAgregar =
                restTemplate.postForEntity("/categorias/agregar", categoriaDTO, CategoriaDTO.class);

        Assertions.assertNotNull(responseAgregar.getBody());
        Assertions.assertEquals(HttpStatus.CREATED, responseAgregar.getStatusCode());

        restTemplate.delete("/categorias/eliminar/" + responseAgregar.getBody().getId());

        ResponseEntity<CategoriaDTO[]> responseEliminar =
                restTemplate.getForEntity("/categorias/obtener", CategoriaDTO[].class);

        CategoriaDTO[] categoriasDespuesEliminar = responseEliminar.getBody();
        if (categoriasDespuesEliminar != null) {
            for (CategoriaDTO cat : categoriasDespuesEliminar) {
                Assertions.assertNotEquals(responseAgregar.getBody().getId(), cat.getId(),
                        "Se encontró la categoría eliminada en la lista después de la eliminación");
            }
        }
    }
}





