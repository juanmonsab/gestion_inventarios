package co.edu.unisabana.api.gestioninventarios.controlador;

import co.edu.unisabana.api.gestioninventarios.dto.CategoriaDTO;
import co.edu.unisabana.api.gestioninventarios.facade.GestionInventariosFacade;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaControlador {

    private final GestionInventariosFacade gestionInventariosFacade;

    public CategoriaControlador(GestionInventariosFacade gestionInventariosFacade) {
        this.gestionInventariosFacade = gestionInventariosFacade;
    }

    @GetMapping("/obtener")
    public List<CategoriaDTO> obtenerCategorias() {
        return gestionInventariosFacade.obtenerTodasLasCategorias();
    }

    @PostMapping("/agregar")
    public ResponseEntity<CategoriaDTO> agregarCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO nuevaCategoria = gestionInventariosFacade.agregarCategoria(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id) {
        try {
            gestionInventariosFacade.eliminarCategoria(id);
            return ResponseEntity.ok("Categoría eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}


