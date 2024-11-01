package com.juan.app_inventario.models;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoria_id;

    @NotBlank(message = "El nombre de la categoría es obligatorio")
    private String nombre;

    private String descripcion;

    //relaciones
    
    @NotNull(message = "Las productos son obligatorias")
    @ManyToMany(mappedBy = "categorias", cascade = CascadeType.ALL)
    private List<Producto> productos;

    public Categoria() {
        // Constructor vacío requerido por JPA
    }

    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return categoria_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
}
