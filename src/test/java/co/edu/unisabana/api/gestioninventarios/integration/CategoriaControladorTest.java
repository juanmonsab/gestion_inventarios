package co.edu.unisabana.api.gestioninventarios.integration;

import co.edu.unisabana.api.gestioninventarios.dto.CategoriaDTO;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CategoriaControladorTest extends AbstractTest {

    String path = "/categorias";
    @Test
    void testAgregarCategoria() {
        String agregarpath = path + "/agregar";
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setNombre("Electrodomesticos");
        ResponseEntity<CategoriaDTO> response = restTemplate.postForEntity(agregarpath,categoriaDTO,CategoriaDTO.class);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        CategoriaDTO categoriaCreada = response.getBody();
        assertNotNull(categoriaCreada);
        assertNotNull(categoriaCreada.getId());
        assertEquals("Electrodomesticos",categoriaCreada.getNombre());
    }
    @Test
    void testObtenerCategorias() {
        String obetenerpath = path + "/obtener";
        ResponseEntity<List<CategoriaDTO>> response = restTemplate.exchange(obetenerpath, HttpMethod.GET, null, new ParameterizedTypeReference<List<CategoriaDTO>>() {
        });
        assertEquals(HttpStatus.OK,response.getStatusCode());
        List<CategoriaDTO> categorias = response.getBody();
        assertNotNull(categorias);
        assertFalse(categorias.isEmpty());
        CategoriaDTO firstCategoria = categorias.get(0);
        assertNotNull(firstCategoria.getId());
        assertNotNull(firstCategoria.getNombre());
    }
}
