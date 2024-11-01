package com.juan.app_inventario.repositories;

import com.juan.app_inventario.models.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRep extends JpaRepository<Producto, Long>{

    @Query("select u from Producto u where u.precio between :precMin and :precMax")
    public List<Producto> obtenerPorRangoPrecio(@Param("precMin") Double precioMinimo, @Param("precMax") Double precioMaximo);

    @Query("select u from Producto u where u.precio > 0")
    public List<Producto> obtenerProductosDisponibles();
    
    @Query("select u from Producto u join u.categorias c where c.categoria_id = :id_categoria")
    public List<Producto> findByCategoria(@Param("id_categoria") Long id_categoria);
    
    public List<Producto> findByNombre(String nombre);

    
    
}
