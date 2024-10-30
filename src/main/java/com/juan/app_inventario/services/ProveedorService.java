package com.juan.app_inventario.services;

import com.juan.app_inventario.models.Proveedor;
import com.juan.app_inventario.repositories.ProveedorRep;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService {
    
    @Autowired
    private ProveedorRep provRep;

    public List<Proveedor> obtenerTodos() {
        return provRep.findAll();
    }

    public Proveedor obtenerPorId(Long id) {
        return provRep.getReferenceById(id);
    }

    public Proveedor actualizar(Long id, Proveedor proveedorActualizado) {
        Proveedor prov = provRep.getReferenceById(id);
        
        prov.setCorreoElectronico(proveedorActualizado.getCorreoElectronico());
        prov.setDireccion(proveedorActualizado.getDireccion());
        prov.setNombre(proveedorActualizado.getNombre());
        prov.setProductos(proveedorActualizado.getProductos());
        prov.setTelefono(proveedorActualizado.getTelefono());
        
        return provRep.save(prov);
    }

    public Proveedor crear(Proveedor proveedor) {
        return provRep.save(proveedor);
    }

    public void eliminar(Long id) {
        provRep.deleteById(id);
    }

    public List<Proveedor> buscar(String nombre, String ciudad) {
        return provRep.buscar(nombre, ciudad);
    }

    public List<Proveedor> obtenerPorCategoria(Long categoriaId) {
        return provRep.obtenerPorCategoria(categoriaId);
    }

    public Map<String, Object> obtenerResumen() {
       return null;
    }

    public Resource exportarProveedores() {
        return null;
    }

    public Proveedor actualizarParcial(Long id, Map<String, Object> campos) {
        Proveedor prov = provRep.getReferenceById(id);
        
        return null;
    }
    
}
