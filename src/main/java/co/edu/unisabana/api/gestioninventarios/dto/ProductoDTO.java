package co.edu.unisabana.api.gestioninventarios.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidad;
    private CategoriaDTO categoria;
}