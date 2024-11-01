package com.juan.app_inventario.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que cero")
    private BigDecimal precio;

    @NotNull(message = "La cantidad en stock es obligatoria")
    @Positive(message = "La cantidad en stock debe ser un número positivo")
    private int cantidadEnStock;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fechaIngreso;

    //relaciones
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "producto_proveedores", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "producto_id"), // Columna de la tabla Producto
            inverseJoinColumns = @JoinColumn(name = "id_proveedor") // Columna de la tabla Categoria
    )
    private List<Proveedor> proveedores;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "producto_categoria", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "producto_id"), // Columna de la tabla Producto
            inverseJoinColumns = @JoinColumn(name = "categoria_id") // Columna de la tabla Categoria
    )
    private List<Categoria> categorias;

    public Producto() {
        // Constructor vacío requerido por JPA
    }

    public Producto(String nombre, String descripcion, BigDecimal precio, int cantidadEnStock, LocalDate fechaIngreso, List<Proveedor> proveedores, List<Categoria> categorias) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadEnStock = cantidadEnStock;
        this.fechaIngreso = fechaIngreso;
        this.proveedores = proveedores;
        this.categorias = categorias;
    }

    public Long getId() {
        return id;
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getCantidadEnStock() {
        return cantidadEnStock;
    }

    public void setCantidadEnStock(int cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

}
