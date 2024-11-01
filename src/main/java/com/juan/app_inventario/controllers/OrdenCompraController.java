package com.juan.app_inventario.controllers;

import com.juan.app_inventario.models.OrdenCompra;
import com.juan.app_inventario.services.OrdenCompraService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ordenes-compra")
public class OrdenCompraController {

    @Autowired
    private OrdenCompraService ordenCompraService;

    //Cambiar estado orden de compra
    @PatchMapping("/estado/{id}")
    public ResponseEntity<OrdenCompra> cambiarEstado(@PathVariable Long id, @RequestParam String nuevoEstado) {
        OrdenCompra ordenCompraActualizada = ordenCompraService.cambiarEstado(id, nuevoEstado);
        return ResponseEntity.ok(ordenCompraActualizada);
    }

    // Actualizar una orden de compra existente
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<OrdenCompra> actualizar(@PathVariable Long id, @Valid @RequestBody OrdenCompra ordenCompra) {
        OrdenCompra ordenCompraActualizada = ordenCompraService.actualizar(id, ordenCompra);
        return ResponseEntity.ok(ordenCompraActualizada);
    }

    // Crear una nueva orden de compra
    @PostMapping("/create")
    public ResponseEntity<OrdenCompra> crear(@Valid @RequestBody OrdenCompra ordenCompra) {
        OrdenCompra nuevaOrdenCompra = ordenCompraService.crear(ordenCompra);
        return ResponseEntity.status(201).body(nuevaOrdenCompra);
    }

    // Eliminar una orden de compra
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ordenCompraService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener una orden de compra por ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<OrdenCompra> obtenerPorId(@PathVariable Long id) {
        OrdenCompra ordenCompra = ordenCompraService.obtenerPorId(id);
        return ResponseEntity.ok(ordenCompra);
    }

    //Filtrar por estado y rango de fechas (opcional cada uno)
    @GetMapping("/buscar")
    public List<OrdenCompra> buscar(
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String fechaDesde,
            @RequestParam(required = false) String fechaHasta) {
        return ordenCompraService.buscarPersonalizado(estado, fechaDesde, fechaHasta);
    }

    //Obtener ordenes de comprar por proveedor
    @GetMapping("/proveedor/{proveedorId}")
    public List<OrdenCompra> obtenerPorProveedor(@PathVariable Long proveedorId) {
        return ordenCompraService.obtenerPorProveedor(proveedorId);
    }

    //Buscar por fecha particular
    @GetMapping("/buscarPorFecha")
    public List<OrdenCompra> buscarFechaParticular(
            @RequestParam LocalDate fecha) {
        return ordenCompraService.buscarFechaParticular(fecha);
    }

    // Obtener todas las órdenes de compra
    @GetMapping("/getAll")
    public List<OrdenCompra> obtenerTodas() {
        return ordenCompraService.obtenerTodas();
    }

}
