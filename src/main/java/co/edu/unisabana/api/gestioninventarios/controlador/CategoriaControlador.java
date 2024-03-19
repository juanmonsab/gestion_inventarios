package co.edu.unisabana.api.gestioninventarios.controlador;

import co.edu.unisabana.api.gestioninventarios.dto.CategoriaDTO;
import co.edu.unisabana.api.gestioninventarios.servicio.ServicioCategoria;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;



import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaControlador {

    private final ServicioCategoria servicioCategoria;

    public CategoriaControlador(ServicioCategoria servicioCategoria) {
        this.servicioCategoria = servicioCategoria;
    }

    @GetMapping("/obtener")
    public List<CategoriaDTO> obtenerCategorias() {
        return servicioCategoria.obtenerTodasLasCategorias();
    }

    @PostMapping("/agregar")
    public ResponseEntity<CategoriaDTO> agregarCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO nuevaCategoria = servicioCategoria.agregarCategoria(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
    }
}