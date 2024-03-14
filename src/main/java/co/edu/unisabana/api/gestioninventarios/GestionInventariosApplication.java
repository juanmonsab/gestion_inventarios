package co.edu.unisabana.api.gestioninventarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"co.edu.unisabana.prueba.gestioninventarios.repositorio"})
@ComponentScan(basePackages = {"co.edu.unisabana.prueba.gestioninventarios"})
@EntityScan(basePackages = {"co.edu.unisabana.prueba.gestioninventarios.modelo"})
public class GestionInventariosApplication {
	public static void main(String[] args) {
		SpringApplication.run(GestionInventariosApplication.class, args);
	}
}
