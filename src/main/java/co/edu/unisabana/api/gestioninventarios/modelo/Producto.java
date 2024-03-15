package co.edu.unisabana.api.gestioninventarios.modelo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Data
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