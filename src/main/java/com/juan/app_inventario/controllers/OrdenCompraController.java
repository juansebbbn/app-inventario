package com.juan.app_inventario.controllers;

import com.juan.app_inventario.models.OrdenCompra;
import com.juan.app_inventario.services.OrdenCompraService;
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

    // Obtener todas las órdenes de compra
    @GetMapping
    public List<OrdenCompra> obtenerTodas() {
        return ordenCompraService.obtenerTodas();
    }

    // Crear una nueva orden de compra
    @PostMapping
    public ResponseEntity<OrdenCompra> crear(@Valid @RequestBody OrdenCompra ordenCompra) {
        OrdenCompra nuevaOrdenCompra = ordenCompraService.crear(ordenCompra);
        return ResponseEntity.status(201).body(nuevaOrdenCompra);
    }

    // Actualizar una orden de compra existente
    @PutMapping("/{id}")
    public ResponseEntity<OrdenCompra> actualizar(@PathVariable Long id, @Valid @RequestBody OrdenCompra ordenCompra) {
        OrdenCompra ordenCompraActualizada = ordenCompraService.actualizar(id, ordenCompra);
        return ResponseEntity.ok(ordenCompraActualizada);
    }

    // Eliminar una orden de compra
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ordenCompraService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener una orden de compra por ID
    @GetMapping("/{id}")
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

    //Cambiar estado orden de compra
    @PatchMapping("/{id}/estado")
    public ResponseEntity<OrdenCompra> cambiarEstado(@PathVariable Long id, @RequestParam String nuevoEstado) {
        OrdenCompra ordenCompraActualizada = ordenCompraService.cambiarEstado(id, nuevoEstado);
        return ResponseEntity.ok(ordenCompraActualizada);
    }

    //Buscar por fecha particular
    @GetMapping("/buscar")
    public List<OrdenCompra> buscarFechaParticular(
            @RequestParam(required = false) String fecha) {
        return ordenCompraService.buscarFechaParticular(fecha);
    }

}
