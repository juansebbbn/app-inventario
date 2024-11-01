package com.juan.app_inventario.models;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import java.util.List;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "movimientos_inventario")
public class MovimientoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_movimiento;

    @NotBlank(message = "El tipo de movimiento es obligatorio")
    private String tipoMovimiento; // Ej. "ENTRADA", "SALIDA"

    @NotNull(message = "La fecha del movimiento es obligatoria")
    private LocalDateTime fechaMovimiento;

    //relaciones
    
    @NotNull(message = "Los productos son obligatorios")
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private List<Producto> productos;

    public MovimientoInventario() {
        // Constructor vacío requerido por JPA
    }

    public MovimientoInventario(String tipoMovimiento, LocalDateTime fechaMovimiento, List<Producto> productos) {
        this.tipoMovimiento = tipoMovimiento;
        this.fechaMovimiento = fechaMovimiento;
        this.productos = productos;
    }
    
    public Long getId() {
        return id_movimiento;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public LocalDateTime getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(LocalDateTime fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }
    
}
