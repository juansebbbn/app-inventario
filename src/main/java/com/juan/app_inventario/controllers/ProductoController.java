package com.juan.app_inventario.controllers;

import com.juan.app_inventario.models.MovimientoInventario;
import com.juan.app_inventario.models.Producto;
import com.juan.app_inventario.services.ProductoService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PatchMapping("/{id}/cantidad")
    public ResponseEntity<Producto> actualizarCantidad(@PathVariable Long id, @RequestParam int cantidad) {
        Producto productoActualizado = productoService.actualizarCantidad(id, cantidad);
        return ResponseEntity.ok(productoActualizado);
    }

    // Actualizar un producto existente
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        Producto productoActualizado = productoService.actualizar(id, producto);
        return ResponseEntity.ok(productoActualizado);
    }

    // Eliminar un producto
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener todos los productos
    @GetMapping("/getAll")
    public ResponseEntity<List<Producto>> obtenerTodos() {
        List<Producto> productos = productoService.obtenerTodos();
        return ResponseEntity.ok(productos);
    }

    // Obtener un producto por ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerPorId(id);
        return ResponseEntity.ok(producto);
    }

    // Obtener un producto por id de la categoria
    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<Producto>> obtenerPorCategoria(@PathVariable Long id) {
        List<Producto> productos = productoService.obtenerPorCategoria(id);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/rango-precio")
    public ResponseEntity<List<Producto>> obtenerPorRangoPrecio(
            @RequestParam Double precioMinimo,
            @RequestParam Double precioMaximo) {
        List<Producto> productos = productoService.obtenerPorRangoPrecio(precioMinimo, precioMaximo);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Producto>> obtenerProductosDisponibles() {
        List<Producto> productos = productoService.obtenerProductosDisponibles();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscarPorNombre")
    public ResponseEntity<List<Producto>> buscarPorNombre(@RequestParam String nombre) {
        List<Producto> productos = productoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(productos);
    }

    //Registrar un movimiento de inventario
    @PostMapping("/registrarMov")
    public ResponseEntity<MovimientoInventario> registrarMovimiento(
            @Valid @RequestBody List<Producto> productos,
            @RequestParam int cantidad,
            @RequestParam String tipo) {

        MovimientoInventario mov = new MovimientoInventario(tipo, LocalDateTime.now(), productos);
        productoService.registrarMovimiento(mov);
        return ResponseEntity.ok(mov);
    }

    @PostMapping("/movimientos")
    public ResponseEntity<List<MovimientoInventario>> registrarMovimientos(@Valid @RequestBody List<MovimientoInventario> movimientos) {
        List<MovimientoInventario> movimientosRegistrados = productoService.registrarMovimientos(movimientos);
        return ResponseEntity.status(201).body(movimientosRegistrados);
    }

    // Crear un nuevo producto
    @PostMapping("/crear")
    public ResponseEntity<Producto> crear(@Valid @RequestBody Producto producto) {
        Producto nuevoProducto = productoService.crear(producto);
        return ResponseEntity.status(201).body(nuevoProducto);
    }

}
