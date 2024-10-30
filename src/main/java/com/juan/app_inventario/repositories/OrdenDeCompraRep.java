package com.juan.app_inventario.repositories;

import com.juan.app_inventario.models.OrdenCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenDeCompraRep extends JpaRepository<OrdenCompra, Long> {
    
}
