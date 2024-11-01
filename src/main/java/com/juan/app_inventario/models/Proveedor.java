package com.juan.app_inventario.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "proveedores")
public class Proveedor { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_proveedor;

    @NotBlank(message = "El nombre del proveedor es obligatorio")
    private String nombre;

    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;

    @NotNull(message = "El teléfono es obligatorio")
    @Positive(message = "El teléfono debe ser un número positivo")
    private String telefono;

    @Email(message = "Correo electrónico debe ser válido")
    private String correoElectronico;

    //relaciones
    
    @ManyToMany(mappedBy = "proveedores", cascade = CascadeType.ALL)
    private List<Producto> productos;

    public Proveedor() {
        // Constructor vacío requerido por JPA
    }

    public Proveedor(String nombre, String ciudad, String telefono, String correoElectronico, List<Producto> productos) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.productos = productos;
    }

    public Long getId_proveedor() {
        return id_proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

}
