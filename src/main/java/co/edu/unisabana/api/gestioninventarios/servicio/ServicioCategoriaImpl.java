package co.edu.unisabana.api.gestioninventarios.servicio;

import co.edu.unisabana.api.gestioninventarios.dto.CategoriaDTO;
import co.edu.unisabana.api.gestioninventarios.modelo.Categoria;
import co.edu.unisabana.api.gestioninventarios.repositorio.CategoriaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioCategoriaImpl implements ServicioCategoria {

    private final CategoriaRepositorio categoriaRepositorio;

    public ServicioCategoriaImpl(CategoriaRepositorio categoriaRepositorio) {
        this.categoriaRepositorio = categoriaRepositorio;
    }

    @Override
    public List<CategoriaDTO> obtenerTodasLasCategorias() {
        List<Categoria> categorias = categoriaRepositorio.findAll();
        return categorias.stream()
                .map(this::convertirACategoriaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO agregarCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaDTO.getNombre());
        categoriaRepositorio.save(categoria);

        return convertirACategoriaDTO(categoria);
    }

    private CategoriaDTO convertirACategoriaDTO(Categoria categoria) {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(categoria.getId());
        categoriaDTO.setNombre(categoria.getNombre());
        return categoriaDTO;
    }
}
