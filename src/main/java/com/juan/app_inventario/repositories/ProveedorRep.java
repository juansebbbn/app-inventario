package com.juan.app_inventario.repositories;

import com.juan.app_inventario.models.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRep extends JpaRepository<Proveedor, Long>{
    
}
