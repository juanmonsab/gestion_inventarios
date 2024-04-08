package co.edu.unisabana.api.gestioninventarios.integration;

import co.edu.unisabana.api.gestioninventarios.dto.ProductoDTO;
import co.edu.unisabana.api.gestioninventarios.integration.AbstractTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class ProductoControladorTest extends AbstractTest {

    String path ="/productos";
    @Autowired
    TestRestTemplate restTemplate;



}