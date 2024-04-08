package co.edu.unisabana.api.gestioninventarios.integration;
import co.edu.unisabana.api.gestioninventarios.dto.CategoriaDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.ResponseEntity;


public class CategoriaControladorTest extends AbstractTest {
    String path = "/categorias";

    @Autowired
    TestRestTemplate restTemplate;


}