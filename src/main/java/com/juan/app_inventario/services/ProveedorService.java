package com.juan.app_inventario.services;

import com.juan.app_inventario.models.Proveedor;
import com.juan.app_inventario.repositories.ProveedorRep;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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
        prov.setCiudad(proveedorActualizado.getCiudad());
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

    public List<Proveedor> buscarPorNombre_O_Ciudad(String nombre, String ciudad) {
        return provRep.buscarPorNombre_O_Ciudad(nombre, ciudad);
    }

    public Map<String, Object> obtenerResumen() {
        // Obtener todos los proveedores
        List<Proveedor> proveedores = provRep.findAll();

        // Inicializar el mapa que contendrá el resumen
        Map<String, Object> resumen = new HashMap<>();

        // Calcular total de proveedores
        int totalProveedores = proveedores.size();

        // Calcular el total de productos asociados a todos los proveedores
        int totalProductos = proveedores.stream()
                .mapToInt(prov -> prov.getProductos().size())
                .sum();

        // Obtener el proveedor con más productos (opcional)
        Proveedor proveedorConMasProductos = proveedores.stream()
                .max((prov1, prov2) -> Integer.compare(prov1.getProductos().size(), prov2.getProductos().size()))
                .orElse(null);

        resumen.put("totalProveedores", totalProveedores);
        resumen.put("totalProductos", totalProductos);

        if (proveedorConMasProductos != null) {
            resumen.put("proveedorConMasProductos", proveedorConMasProductos.getNombre());
            resumen.put("productosDelProveedorConMas", proveedorConMasProductos.getProductos().size());
        }

        return resumen;
    }

    public Proveedor actualizarParcial(Long id, Map<String, Object> campos) {
        // Obtener el proveedor desde el repositorio
        Proveedor prov = provRep.findById(id).orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        // Iterar sobre el mapa y actualizar los campos
        campos.forEach((nombreCampo, valorCampo) -> {
            try {
                // Obtener el campo de la clase Proveedor
                Field campo = Proveedor.class.getDeclaredField(nombreCampo);
                campo.setAccessible(true); // Hacer accesible el campo privado

                // Asignar el nuevo valor al campo del objeto proveedor
                campo.set(prov, valorCampo);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Error al actualizar el campo: " + nombreCampo, e);
            }
        });

        // Guardar el proveedor actualizado en el repositorio
        return provRep.save(prov);
    }

}
