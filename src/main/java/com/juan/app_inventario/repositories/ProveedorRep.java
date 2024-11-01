package com.juan.app_inventario.repositories;

import com.juan.app_inventario.models.Proveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRep extends JpaRepository<Proveedor, Long>{

    @Query("select u from Proveedor u where u.nombre = :nombreP OR u.ciudad = :ciudadP")
    public List<Proveedor> buscarPorNombre_O_Ciudad(@Param("nombreP") String nombre, @Param("ciudadP") String ciudad);
    
}
