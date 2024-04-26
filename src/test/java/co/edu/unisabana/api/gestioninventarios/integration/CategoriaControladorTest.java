package co.edu.unisabana.api.gestioninventarios.integration;
import co.edu.unisabana.api.gestioninventarios.dto.CategoriaDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.assertj.core.api.Fail.fail;


public class CategoriaControladorTest extends AbstractTest {
    String path = "/categorias";

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;


}



