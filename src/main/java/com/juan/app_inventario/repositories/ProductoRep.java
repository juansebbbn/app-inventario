package com.juan.app_inventario.repositories;

import com.juan.app_inventario.models.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRep extends JpaRepository<Producto, Long>{

    public List<Producto> getProductosPorCategoria();

    public List<Producto> obtenerPorRangoPrecio(Double precioMinimo, Double precioMaximo);
    
}
