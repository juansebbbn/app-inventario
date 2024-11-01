package com.juan.app_inventario.services;

import com.juan.app_inventario.models.OrdenCompra;
import com.juan.app_inventario.repositories.OrdenDeCompraRep;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenCompraService {
    
    @Autowired
    private OrdenDeCompraRep ordenRep;
    
    public List<OrdenCompra> obtenerTodas() {
        return ordenRep.findAll();
    }
    
    public OrdenCompra obtenerPorId(Long id) {
        return ordenRep.getReferenceById(id);
    }
    
    public OrdenCompra crear(OrdenCompra ordenCompra) {
        return ordenRep.save(ordenCompra);
    }
    
    public OrdenCompra actualizar(Long id, OrdenCompra ordenCompraActualizada) {
        OrdenCompra orden = ordenRep.getReferenceById(id);
        
        orden.setEstado(ordenCompraActualizada.getEstado());
        orden.setFechaOrden(ordenCompraActualizada.getFechaOrden());
        orden.setProductos(ordenCompraActualizada.getProductos());
        orden.setProveedor(ordenCompraActualizada.getProveedor());
   
        return ordenRep.save(orden);
    }
    
    public void eliminar(Long id) {
        ordenRep.deleteById(id);
    }
    
    public List<OrdenCompra> buscarPersonalizado(String estado, String fechaDesde, String fechaHasta) {
        return ordenRep.buscarPersonalizado(estado, fechaDesde, fechaHasta);
    }
    
    public List<OrdenCompra> obtenerPorProveedor(Long proveedorId) {
        return ordenRep.obtenerPorProveedor(proveedorId);
    }
    
    public OrdenCompra cambiarEstado(Long id, String nuevoEstado) {
        OrdenCompra orden = ordenRep.getReferenceById(id);
        
        orden.setEstado(nuevoEstado);
        
        return ordenRep.save(orden);
    }
    
    public List<OrdenCompra> buscarFechaParticular(LocalDate fecha) {
        return ordenRep.findByfechaOrden(fecha);
    }
    
}
