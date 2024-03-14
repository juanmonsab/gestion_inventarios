package co.edu.unisabana.api.gestioninventarios.servicio;

import co.edu.unisabana.api.gestioninventarios.dto.CategoriaDTO;

import java.util.List;

public interface ServicioCategoria {

    List<CategoriaDTO> obtenerTodasLasCategorias();

    CategoriaDTO agregarCategoria(CategoriaDTO categoriaDTO);
}
