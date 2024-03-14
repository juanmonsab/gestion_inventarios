package co.edu.unisabana.api.gestioninventarios.repositorio;

import co.edu.unisabana.api.gestioninventarios.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoria_Id(Long categoriaId);
}