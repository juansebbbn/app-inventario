package com.juan.app_inventario.services;

import com.juan.app_inventario.models.MovimientoInventario;
import com.juan.app_inventario.models.Producto;
import com.juan.app_inventario.repositories.MovimientoRep;
import com.juan.app_inventario.repositories.ProductoRep;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRep prodRep;

    @Autowired
    private MovimientoRep movRep;

    public List<Producto> obtenerTodos() {
        return prodRep.findAll();
    }

    public Producto obtenerPorId(Long id) {
        return prodRep.getReferenceById(id);
    }

    public Producto crear(Producto producto) {
        return prodRep.save(producto);
    }

    public Producto actualizar(Long id, Producto productoAct) {
        Producto prod = prodRep.getReferenceById(id);

        prod.setCantidadEnStock(productoAct.getCantidadEnStock());
        prod.setCategoria(productoAct.getCategoria());
        prod.setDescripcion(productoAct.getDescripcion());
        prod.setFechaIngreso(productoAct.getFechaIngreso());
        prod.setNombre(productoAct.getNombre());
        prod.setPrecio(productoAct.getPrecio());

        return prodRep.save(prod);
    }

    public void eliminar(Long id) {
        prodRep.deleteById(id);
    }

    public List<Producto> obtenerPorCategoria(String categoria) {
        return prodRep.getProductosPorCategoria();
    }

    public List<Producto> obtenerPorRangoPrecio(Double precioMinimo, Double precioMaximo) {
        return prodRep.obtenerPorRangoPrecio(precioMinimo, precioMaximo);
    }

    public List<Producto> obtenerProductosDisponibles() {
        return prodRep.obtenerProductosDisponibles();
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return prodRep.buscarPorNombre(nombre);
    }

    public Producto actualizarCantidad(Long id, int cantidad) {
        Producto prod = prodRep.getReferenceById(id);

        prod.setCantidadEnStock(cantidad);

        return prodRep.save(prod);
    }

    public void registrarMovmiento(MovimientoInventario mov) {
        movRep.save(mov);
    }

    public List<MovimientoInventario> registrarMovimientos(List<MovimientoInventario> movimientos) {
        for (MovimientoInventario movimiento : movimientos) {
            movRep.save(movimiento);
        }
        
        return movimientos;
    }

}
