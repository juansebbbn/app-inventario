package com.juan.app_inventario.models;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ubicaciones")
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ubicacion;

    @NotBlank(message = "El nombre de la ubicación es obligatorio")
    private String nombre;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;
  
    //relaciones
    
    @NotNull(message = "Los productos son obligatorios")
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private List<Producto> productos;

    public Ubicacion() {
        // Constructor vacío requerido por JPA
    }

    public Ubicacion(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Long getId() {
        return id_ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
