package co.edu.unisabana.api.gestioninventarios.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidad;
    private CategoriaDTO categoria;
}
