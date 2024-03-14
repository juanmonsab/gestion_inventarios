package co.edu.unisabana.api.gestioninventarios.repositorio;

import co.edu.unisabana.api.gestioninventarios.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
}