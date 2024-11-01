package com.juan.app_inventario.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "ordenes_compra")
public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orden_compra_id;

    @NotNull(message = "La fecha de la orden es obligatoria")
    private LocalDate fechaOrden;

    @NotBlank(message = "El estado de la orden es obligatorio")
    private String estado; // Ej. "PENDIENTE", "COMPLETADA", "CANCELADA"

    //relaciones
    
    @NotNull(message = "El proveedor es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

    @NotNull(message = "Los productos son obligatorios")
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private List<Producto> productos;

    public OrdenCompra() {
        // Constructor vacío requerido por JPA
    }

    public OrdenCompra(LocalDate fechaOrden, String estado, Proveedor proveedor, List<Producto> productos) {
        this.fechaOrden = fechaOrden;
        this.estado = estado;
        this.proveedor = proveedor;
        this.productos = productos;
    }

    public Long getOrden_compra_id() {
        return orden_compra_id;
    }

    public LocalDate getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(LocalDate fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

}
