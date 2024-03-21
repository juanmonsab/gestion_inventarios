package co.edu.unisabana.api.gestioninventarios.service;

import co.edu.unisabana.api.gestioninventarios.dto.CategoriaDTO;
import co.edu.unisabana.api.gestioninventarios.modelo.Categoria;
import co.edu.unisabana.api.gestioninventarios.repositorio.CategoriaRepositorio;
import co.edu.unisabana.api.gestioninventarios.servicio.ServicioCategoriaImpl;
import static org.mockito.Mockito.doReturn;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)

public class ServicioCategoriaImplTest {

    @InjectMocks
    ServicioCategoriaImpl servicio;

    @Mock
    CategoriaRepositorio repositorio;

    @Test
    void DadoCategoriasEnRepositorio_CuandoObtenerTodasLasCategorias_EntoncesRetornaListaCategorias() {

        Categoria categoria1 = new Categoria();
        Categoria categoria2 = new Categoria();
        categoria1.setId(1L);
        categoria2.setId(2L);
        categoria1.setNombre("Productos de Aseo");
        categoria2.setNombre("Farmacia");
        List<Categoria> categorias = new ArrayList<>();
        categorias.add(categoria1);
        categorias.add(categoria2);
        doReturn(categorias).when(repositorio).findAll();
        List<CategoriaDTO> categoriaDTOS =servicio.obtenerTodasLasCategorias();
        assertEquals(categorias.size(),categoriaDTOS.size());
    }

    @Test
    void DadoCategoriaDTO_CuandoAgregarCategoria_EntoncesRetornaCategoriaDTO() {

        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(1L);
        categoriaDTO.setNombre("Aseo");
        CategoriaDTO resultado = servicio.agregarCategoria(categoriaDTO);
        assertEquals(categoriaDTO.getId(), resultado.getId());
        assertEquals(categoriaDTO.getNombre(), resultado.getNombre());
    }

    @Test
    void DadoCategoria_CaundoConvertirCategoriaDTO_EntoncesRetornaCategoriaDTO() {

        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNombre("Prueba");
        CategoriaDTO categoriaDTO =servicio.convertirACategoriaDTO(categoria);
        assertEquals(categoria.getId(),categoriaDTO.getId());
        assertEquals(categoria.getNombre(),categoriaDTO.getNombre());
    }
}
