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

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodos() {
        List<Producto> productos = productoService.obtenerTodos();
        return ResponseEntity.ok(productos);
    }

    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<Producto> crear(@Valid @RequestBody Producto producto) {
        Producto nuevoProducto = productoService.crear(producto);
        return ResponseEntity.status(201).body(nuevoProducto);
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        Producto productoActualizado = productoService.actualizar(id, producto);
        return ResponseEntity.ok(productoActualizado);
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerPorId(id);
        return ResponseEntity.ok(producto);
    }

    // Obtener un producto por Categoria
    @GetMapping("/productos/categoria/{categoria}")
    public ResponseEntity<List<Producto>> obtenerPorCategoria(@PathVariable String categoria) {
        List<Producto> productos = productoService.obtenerPorCategoria(categoria);
        return ResponseEntity.ok(productos);
    }

    //Registrar un movimiento de inventario
    @PostMapping
    public ResponseEntity<MovimientoInventario> registarMOvimiento(@Valid @RequestBody long id_producto, int cantidad, String tipo) {
        Producto prod = productoService.obtenerPorId(id_producto);
        MovimientoInventario mov = new MovimientoInventario(prod, cantidad, tipo, LocalDateTime.now());
        productoService.registrarMovmiento(mov);
        return ResponseEntity.status(201).body(mov);
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

    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarPorNombre(@RequestParam String nombre) {
        List<Producto> productos = productoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(productos);
    }

    @PatchMapping("/{id}/cantidad")
    public ResponseEntity<Producto> actualizarCantidad(@PathVariable Long id, @RequestBody int cantidad) {
        Producto productoActualizado = productoService.actualizarCantidad(id, cantidad);
        return ResponseEntity.ok(productoActualizado);
    }

    @PostMapping("/movimientos")
    public ResponseEntity<List<MovimientoInventario>> registrarMovimientos(@Valid @RequestBody List<MovimientoInventario> movimientos) {
        List<MovimientoInventario> movimientosRegistrados = productoService.registrarMovimientos(movimientos);
        return ResponseEntity.status(201).body(movimientosRegistrados);
    }

}
