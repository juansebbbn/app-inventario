package com.juan.app_inventario.repositories;

import com.juan.app_inventario.models.MovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRep extends JpaRepository<MovimientoInventario, Long>{
    
}
