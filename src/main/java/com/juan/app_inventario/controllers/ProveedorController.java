package com.juan.app_inventario.controllers;

import com.juan.app_inventario.models.Proveedor;
import com.juan.app_inventario.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    //Actualizar personalizado un proveedor
    @PatchMapping("/actualizarPersonalizado/{id}")
    public ResponseEntity<Proveedor> actualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        Proveedor proveedorActualizado = proveedorService.actualizarParcial(id, campos);
        return ResponseEntity.ok(proveedorActualizado);
    }

    // Crear un nuevo proveedor
    @PostMapping("/create")
    public ResponseEntity<Proveedor> crear(@Valid @RequestBody Proveedor proveedor) {
        Proveedor nuevoProveedor = proveedorService.crear(proveedor);
        return ResponseEntity.status(201).body(nuevoProveedor);
    }

    // Actualizar un proveedor existente
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Proveedor> actualizar(@PathVariable Long id, @Valid @RequestBody Proveedor proveedor) {
        Proveedor proveedorActualizado = proveedorService.actualizar(id, proveedor);
        return ResponseEntity.ok(proveedorActualizado);
    }

    // Eliminar un proveedor
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        proveedorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener un proveedor por ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<Proveedor> obtenerPorId(@PathVariable Long id) {
        Proveedor proveedor = proveedorService.obtenerPorId(id);
        return ResponseEntity.ok(proveedor);
    }

    //Filtrar proveedor personalizado
    @GetMapping("/buscar")
    public List<Proveedor> buscar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String ciudad) {
        return proveedorService.buscarPorNombre_O_Ciudad(nombre, ciudad);
    }

    //Obtener resumen de los proveedores
    @GetMapping("/resumen")
    public ResponseEntity<Map<String, Object>> obtenerResumen() {
        Map<String, Object> resumen = proveedorService.obtenerResumen();
        return ResponseEntity.ok(resumen);
    }

    // Obtener todos los proveedores
    @GetMapping("/getAll")
    public List<Proveedor> obtenerTodos() {
        return proveedorService.obtenerTodos();
    }

}
