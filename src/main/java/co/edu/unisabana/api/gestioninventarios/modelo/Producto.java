package co.edu.unisabana.api.gestioninventarios.modelo;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidad;

    @ManyToOne
    private Categoria categoria;

    public Producto() {
    }
}